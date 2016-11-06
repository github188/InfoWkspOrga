package com.sgu.infowksporga.jfx.y_service.remote.workspace.cu;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.control.list.AbstractItemVo;
import com.sgu.core.framework.gui.jfx.control.tree.GTreeView;
import com.sgu.core.framework.gui.jfx.service.AbstractBusinessFacade;
import com.sgu.core.framework.gui.jfx.util.UtilApplication;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceIn;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceOut;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTreeItem;
import com.sgu.infowksporga.jfx.perspective.tree.vo.WorkspaceItemVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilWorkspace;
import com.sgu.infowksporga.jfx.workspace.dlg.mvc.WorkspaceDlgScreen;
import com.sgu.infowksporga.rest.RestServiceMapping;
import com.sgu.infowksporga.util.OrderManager;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TreeItem;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class AbstractSavingWorkspaceFacade.
 */
@Service
@Slf4j
public abstract class AbstractSavingWorkspaceFacade extends AbstractBusinessFacade<SaveWorkspaceOut, WorkspaceDlgScreen> {

  /** The last workspace position. */
  protected PerspectiveTreeItem newWorkspacePosition;

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSavingWorkspaceFacade.class);

  /**
   * Constructor<br>
   */
  public AbstractSavingWorkspaceFacade() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SaveWorkspaceOut execute(final WorkspaceDlgScreen screen) throws TechnicalException, BusinessException {

    // -------- build Workspace informations
    final Workspace workspaceFromMdl = screen.model().mapModelToWorkspace();

    // Get the current workspace parent (used for workspace move if new workspace parent has changed)
    final Workspace currentSelectedWorkspace = GUISessionProxy.getCurrentWorkspace().getWorkspace();
    Workspace prevWorkspaceParent = null;
    if (currentSelectedWorkspace != null && currentSelectedWorkspace.getId().equals(workspaceFromMdl.getId())) {
      prevWorkspaceParent = currentSelectedWorkspace.getParent();
    }

    // insert or move (if wksp parent selected change)  Current workspace Item in tree view
    final GTreeView<PerspectiveTreeItem> tree = GUISessionProxy.getPerspectiveScreen().getView().getTreeWorkspaces();
    placeWorkspaceItemIntoParentTreeNode(tree, prevWorkspaceParent, workspaceFromMdl.getParent(), workspaceFromMdl, screen.model().getWkspPositionProperty().intValue());

    // Compute the new workspace global order final Map<String, Integer> newWorkspaceIdOrder = wkspOrder.getNewWorkspaceIdOrder();
    final OrderManager orderMng = new OrderManager(0);
    final Map<String, Integer> newWorkspacesOrder = new LinkedHashMap<String, Integer>(10);
    buildNewOrderForAllWorkspaces(newWorkspacesOrder, (PerspectiveTreeItem) tree.getRoot(), orderMng);

    // -------- build Workspace Pivot IN
    final SaveWorkspaceIn in = new SaveWorkspaceIn();
    in.setWorkspace(workspaceFromMdl);
    in.setNewWorkspacesOrder(newWorkspacesOrder);
    // Set Perspective id to build/rebuild workspace order
    in.setPerspectiveId(GUISessionProxy.getCurrentPerspectiveEntity().getId());
    // Prepare the pivot in to be persisted
    in.setUserInfo(GUISessionProxy.getGuiSession().getCurrentUser());

    //--------------
    finalizePivotInDataBeforeCallingService(in);

    //--------------
    log.debug("Treatment Date : {}", in.getTreatmentDate());
    log.debug("UserInfo : {}", in.getUserInfo());
    log.debug("Workspace : {}", in.getWorkspace());
    log.debug("New Order For All Workspaces : {}", in.getNewWorkspacesOrder());
    log.debug("Perspective Id : {}", in.getPerspectiveId());

    // Call the service
    final SaveWorkspaceOut out = (SaveWorkspaceOut) UtilApplication.callRestBusinessService(RestServiceMapping.URL_SERVICE_SAVE_WORKSPACE, in, SaveWorkspaceOut.class);

    return out;

  }

  /**
   * Builds the new order for all workspaces.
   *
   * @param newWorkspaceIdOrder the new workspace id order
   * @param parent the parent
   * @param orderMng the order mng
   */
  public static final void buildNewOrderForAllWorkspaces(final Map<String, Integer> newWorkspaceIdOrder, final PerspectiveTreeItem parent, final OrderManager orderMng) {
    if (parent.getWorkspace().getId() == null) {
      throw new TechnicalException("Workspace id must not be null");
    }
    else {
      final int nextOrder = orderMng.getNextOrder();
      newWorkspaceIdOrder.put(parent.getWorkspace().getId(), nextOrder);
    }

    final ObservableList<TreeItem<AbstractItemVo>> children = parent.getChildren();
    for (final TreeItem<AbstractItemVo> child : children) {
      buildNewOrderForAllWorkspaces(newWorkspaceIdOrder, (PerspectiveTreeItem) child, orderMng);
    }

  }

  /**
   * Adds the new workspace item to parent tree node in case of creation
   * Or move the workspace to the new parent in case of modification.
   *
   * @param tree the tree
   * @param oldWkspLoc the old wksp loc if this param is not null check if we don't need to move the workspace in treeView
   * @param workspaceDest the workspace dest
   * @param workspace the workspace
   */
  protected void placeWorkspaceItemIntoParentTreeNode(final GTreeView<PerspectiveTreeItem> tree, Workspace oldWkspLoc, final Workspace workspaceDest,
  final Workspace workspace, Integer positionInParent) {

    if (oldWkspLoc == null) { // Creation
      oldWkspLoc = workspaceDest; // We simulate an update to get the same behaviour between an update an a creation
    }

    // Search The selected Workspace
    final PerspectiveTreeItem oldTreeItemLoc = UtilWorkspace.searchWorkspaceTreeItem((PerspectiveTreeItem) tree.getRoot(), oldWkspLoc, false);
    final PerspectiveTreeItem destinationTreeItem = UtilWorkspace.searchWorkspaceTreeItem((PerspectiveTreeItem) tree.getRoot(), workspaceDest, false);
    PerspectiveTreeItem selectedTreeItem = UtilWorkspace.searchWorkspaceTreeItem((PerspectiveTreeItem) tree.getRoot(), workspace, false);

    if (selectedTreeItem == null) {// Creation
      selectedTreeItem = new PerspectiveTreeItem();
      selectedTreeItem.setWorkspace(workspace);
    }

    // Cut node from his previous location
    oldTreeItemLoc.getChildren().remove(selectedTreeItem);

    // Check the wanted position of this new Workspace in the parent
    // if the order of this workspace is not defined we use by default the last index of the child parent.
    if (positionInParent == null || positionInParent > destinationTreeItem.getChildren().size()) {
      positionInParent = destinationTreeItem.getChildren().size(); // used only here to add new workspace at the correct parent index. The order is re-computed from the root node
    }
    // Paste the child in his new parent at the correct index
    destinationTreeItem.getChildren().add(positionInParent, selectedTreeItem);

    // keep the moved item
    newWorkspacePosition = selectedTreeItem;

  }

  /**
   * Place workspace item into parent tree node.
   *
   * @param tree the tree
   * @param workspaceDest the workspace dest
   * @param workspace the workspace
   */
  protected void placeWorkspaceItemIntoParentTreeNode(final GTreeView<PerspectiveTreeItem> tree, final Workspace workspaceDest, final Workspace workspace,
  final Integer positionInParent) {
    placeWorkspaceItemIntoParentTreeNode(tree, null, workspaceDest, workspace, positionInParent);
  }

  /**
   * Finalize pivot in data before calling service.
   *
   * @param in the in
   */
  protected abstract void finalizePivotInDataBeforeCallingService(final SaveWorkspaceIn in);

  @Override
  public void refreshScreen(final SaveWorkspaceOut out, final WorkspaceDlgScreen screen, final StringBuilder reportMessages, final ProgressBar progressBar) {
    if (out != null) {
      // update the ID, creation info of the workspace created in database
      newWorkspacePosition.setWorkspace(out.getWorkspace());

      Platform.runLater(() -> {
        final WorkspaceItemVo workspaceItemVo = newWorkspacePosition.getWorkspaceItemVo();
        // To force tree item refresh
        // Tips origin : @see{https://tagmycode.com/snippet/1533/force-treeview-cell-update#.WBHzgWhxqHs}
        newWorkspacePosition.setValue(null);
        newWorkspacePosition.setValue(workspaceItemVo);

        final GTreeView<PerspectiveTreeItem> tree = GUISessionProxy.getPerspectiveScreen().getView().getTreeWorkspaces();
        tree.getSelectionModel().select(newWorkspacePosition);
      });

    }
  }

}
