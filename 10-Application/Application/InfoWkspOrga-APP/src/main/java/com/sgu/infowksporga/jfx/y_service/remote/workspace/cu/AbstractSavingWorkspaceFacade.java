package com.sgu.infowksporga.jfx.y_service.remote.workspace.cu;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.control.list.AbstractItemVo;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.control.tree.GTreeView;
import com.sgu.core.framework.gui.jfx.service.AbstractBusinessFacade;
import com.sgu.core.framework.gui.jfx.util.UtilApplication;
import com.sgu.infowksporga.business.dto.PerspectiveWorkspaceOrderDto;
import com.sgu.infowksporga.business.dto.WorkspaceDto;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceIn;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceOut;
import com.sgu.infowksporga.jfx.perspective.cbb.CbbPerspectiveItemVo;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTreeItem;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.workspace.dlg.mvc.WorkspaceDlgScreen;
import com.sgu.infowksporga.rest.RestServiceMapping;
import com.sgu.infowksporga.util.OrderManager;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class AbstractSavingWorkspaceFacade.
 */
@Service
@Slf4j
public abstract class AbstractSavingWorkspaceFacade extends AbstractBusinessFacade<SaveWorkspaceOut, WorkspaceDlgScreen> {

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
    final SaveWorkspaceIn in = new SaveWorkspaceIn();

    // -------- First define the default workspace in XML
    final GDockPane dockPane = GUISessionProxy.getApplication().getApplicationScreen().getView().getDockPane();
    final String xmlDock = buildXmlDock();
    log.debug(xmlDock);

    // -------- Secondly  build Workspace informations
    final Workspace workspace = screen.model().mapModelToWorkspace();
    workspace.setLayout(xmlDock.getBytes());
    workspace.setWidth(dockPane.getWidth());
    workspace.setHeight(dockPane.getHeight()); // Not used at this time

    // Workspace DTO and pivot to build for saving
    final WorkspaceDto workspaceDtoToSave = new WorkspaceDto();
    workspaceDtoToSave.setWorkspace(workspace);
    in.setWorkspaceDto(workspaceDtoToSave);

    // Set Perspective id to build/rebuild workspace order
    final PerspectiveWorkspaceOrderDto newWorkspaceIdOrder = new PerspectiveWorkspaceOrderDto();
    in.setPerspectiveWorkspaceOrderDto(newWorkspaceIdOrder);
    newWorkspaceIdOrder.setPerspectiveId(GUISessionProxy.getCurrentPerspectiveEntity().getId());

    // Retrieve old PerspectiveWorkspace order ==> To update only impacted workspaces in the hierarchy
    final CbbPerspectiveItemVo selectedPerspective = (CbbPerspectiveItemVo) GUISessionProxy.getApplication().getApplicationScreen().getPerspectiveScreen().view()
                                                                                           .getCbbPerspective().getSelectionModel().getSelectedItem();
    newWorkspaceIdOrder.setOldWorkspaceIdOrder(selectedPerspective.getCurrentWorkspaceIdOrder());

    // Tree ref
    final GTreeView<PerspectiveTreeItem> tree = GUISessionProxy.getPerspectiveScreen().getView().getTreeWorkspaces();

    // Only in case of creation (in other case method is empty)
    addNewWorkspaceItemToParentTreeNode(tree, workspace, workspaceDtoToSave);

    // Compute the new workspace global order final Map<String, Integer> newWorkspaceIdOrder = wkspOrder.getNewWorkspaceIdOrder();
    final OrderManager orderMng = new OrderManager(0);
    buildNewOrderForAllWorkspaces(in.getPerspectiveWorkspaceOrderDto().getNewWorkspaceIdOrder(), (PerspectiveTreeItem) tree.getRoot(), orderMng);

    // Prepare the pivot in to be persisted
    in.setUserInfo(GUISessionProxy.getGuiSession().getCurrentUser());
    in.setPerspectiveWorkspaceOrderDto(newWorkspaceIdOrder);

    //--------------
    finalizePivotInDataBeforeCallingService(in);

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
      newWorkspaceIdOrder.put(parent.getWorkspace().getId(), orderMng.getNextOrder());
    }

    final ObservableList<TreeItem<AbstractItemVo>> children = parent.getChildren();
    for (final TreeItem<AbstractItemVo> child : children) {
      buildNewOrderForAllWorkspaces(newWorkspaceIdOrder, (PerspectiveTreeItem) child, orderMng);
    }

  }

  /**
   * Builds the xml dock.
   *
   * @return the string
   */
  protected abstract String buildXmlDock();

  /**
   * Adds the new workspace item to parent tree node.
   *
   * @param tree the tree
   * @param workspace the workspace
   * @param workspaceDtoToSave the workspace dto to save
   */
  protected void addNewWorkspaceItemToParentTreeNode(final GTreeView<PerspectiveTreeItem> tree, final Workspace workspace, final WorkspaceDto workspaceDtoToSave) {
    // By default do nothing, should be used only in creation
  }

  /**
   * Finalize pivot in data before calling service.
   *
   * @param in the in
   */
  protected abstract void finalizePivotInDataBeforeCallingService(final SaveWorkspaceIn in);

}
