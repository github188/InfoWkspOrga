package com.sgu.infowksporga.jfx.zfacade.remote.workspace;

import java.util.Date;

import javax.swing.ProgressMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.IDisplayMode;
import com.sgu.core.framework.gui.swing.util.UtilNotificationMsg;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.dto.PerspectiveWorkspaceOrderDto;
import com.sgu.infowksporga.business.dto.WorkspaceDto;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceIn;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceOut;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTree;
import com.sgu.infowksporga.jfx.perspective.tree.node.PerspectiveTreeNode;
import com.sgu.infowksporga.jfx.perspective.tree.nodevo.WorkspaceNodeVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.workspace.dlg.properties.WorkspaceDlg;
import com.sgu.infowksporga.jfx.zfacade.AbstractBusinessFacade;
import com.sgu.infowksporga.rest.RestServiceMapping;

/**
 * Description : SaveWorkspacePropertiesFacade class<br>
 */
@Service
public class SaveWorkspacePropertiesFacade extends AbstractBusinessFacade<SaveWorkspaceOut, WorkspaceDlg> {

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SaveWorkspacePropertiesFacade.class);

  /**
   * Constructor<br>
   */
  public SaveWorkspacePropertiesFacade() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SaveWorkspaceOut execute(final WorkspaceDlg container) throws TechnicalException, BusinessException {
    final PerspectivePanel perspective = (PerspectivePanel) GUISessionProxy.getCurrentPerspective();
    PerspectiveTree tree = (PerspectiveTree) perspective.getTree();

    // Get current workspace from dialog
    final Workspace editedWorkspace = container.bindUiComponentToEntity();
    final WorkspaceDto workspaceDtoToSave = new WorkspaceDto();
    workspaceDtoToSave.setWorkspace(editedWorkspace);

    // Get the current workspaces order
    PerspectiveWorkspaceOrderDto currentWorkspaceOrderDto = tree.reIndexPerspectiveWorkspaceOrder();

    if ((container.getDisplayMode() == IDisplayMode.MODE_CREATE || container.getDisplayMode() == IDisplayMode.MODE_COPY)
        && UtilString.isBlank(editedWorkspace.getId())) {
      // it's a creation or duplicate add workspace to his parent in the tree before new computation
      final PerspectiveTreeNode selectedParentNode = tree.searchTreeNodeRecursively((PerspectiveTreeNode) tree.getModel().getRoot(),
                                                                                    editedWorkspace.getParent().getId());
      final PerspectiveTreeNode newChild = new PerspectiveTreeNode(new Date().getTime(), editedWorkspace);
      selectedParentNode.add(newChild);
    }
    else if (container.getDisplayMode() == IDisplayMode.MODE_UPDATE) {
      //A possible workspace move has been done in case of update
      final PerspectiveTreeNode selectedNode = (PerspectiveTreeNode) tree.getLastSelectedNode();
      final Workspace currentWorkspace = selectedNode.getWorkspace();

      // Check if a move has been done 
      if (currentWorkspace.getParent().getId().equals(editedWorkspace.getParent().getId()) == false) {
        // remove node from is current parent
        ((PerspectiveTreeNode) selectedNode.getParent()).remove(selectedNode);

        // retrieve new parent and add workspace to it
        final PerspectiveTreeNode newParentNode = tree.searchTreeNodeRecursively((PerspectiveTreeNode) tree.getModel().getRoot(),
                                                                                 editedWorkspace.getParent().getId());
        selectedNode.setWorkspace(new WorkspaceNodeVo(new Date().getTime(), editedWorkspace));
        newParentNode.add(selectedNode);
      }
    }

    // Get the NEW workspaces order
    PerspectiveWorkspaceOrderDto newWorkspaceOrderDto = tree.reIndexPerspectiveWorkspaceOrder();
    newWorkspaceOrderDto.setOldWorkspaceIdOrder(currentWorkspaceOrderDto.getNewWorkspaceIdOrder());

    // Prepare the pivot in to be persisted
    final SaveWorkspaceIn in = new SaveWorkspaceIn(workspaceDtoToSave);
    in.setUserLogin(GUISessionProxy.getGuiSession().getCurrentUser());
    in.setPerspectiveWorkspaceOrderDto(newWorkspaceOrderDto);

    // Call the service
    final SaveWorkspaceOut out = (SaveWorkspaceOut) UtilInfoWrkspOrga.callRestBusinessService(RestServiceMapping.URL_SERVICE_SAVE_WORKSPACE,
                                                                                             in, SaveWorkspaceOut.class);

    // Update workspaceId if it's a creation
    if (UtilString.isBlank(editedWorkspace.getId())) {
      editedWorkspace.setId(out.getWorkspaceDto().getWorkspace().getId());
    }

    return out;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "workspace.saving.completed", value = "La sauvegarde du workspace <b>{0}</b> est terminée."), // Force /n
  })
  public void refreshScreen(final SaveWorkspaceOut out, final WorkspaceDlg container, final StringBuilder reportMessages,
  final ProgressMonitor monitor) {
    if (out != null) {
      // Display message Workspace saved
      final String message = I18nHelperApp.getMessage("workspace.saving.completed", out.getWorkspaceDto().getWorkspace().getName());
      UtilNotificationMsg.displayMessage(message);

      final PerspectivePanel perspective = (PerspectivePanel) GUISessionProxy.getCurrentPerspective();
      PerspectiveTree tree = (PerspectiveTree) perspective.getTree();

      // it's a creation add workspace to his parent in the tree
      if (container.getDisplayMode() == IDisplayMode.MODE_CREATE || container.getDisplayMode() == IDisplayMode.MODE_COPY) {
        //We disable the listener because the refresh reload all the tree and fire not wanted events
        tree.refeshTreeView(tree.getPerspectiveTreeSelectionListener());
      }
      else if (container.getDisplayMode() == IDisplayMode.MODE_UPDATE) {
        tree.refeshTreeView(tree.getPerspectiveTreeSelectionListener());

      }

      container.dispose();

    }
  }

}
