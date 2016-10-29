package com.sgu.infowksporga.jfx.y_service.remote.workspace.cu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.control.tree.GTreeView;
import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceIn;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceOut;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTreeItem;
import com.sgu.infowksporga.jfx.perspective.tree.vo.WorkspaceItemVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilView;
import com.sgu.infowksporga.jfx.util.UtilWorkspace;
import com.sgu.infowksporga.jfx.workspace.dlg.mvc.WorkspaceDlgScreen;

import javafx.scene.control.ProgressBar;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class UpdateWorkspaceFacade.
 */
@Service
@Slf4j
public class UpdateWorkspaceFacade extends AbstractSavingWorkspaceFacade {

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(UpdateWorkspaceFacade.class);

  /**
   * Constructor<br>
   */
  public UpdateWorkspaceFacade() {
    super();
  }

  @Override
  protected String buildXmlDock() {
    final String xmlDock = UtilWorkspace.buildXmlDock();
    return xmlDock;
  }

  @Override
  protected void finalizePivotInDataBeforeCallingService(final SaveWorkspaceIn in) {
    // Build Views from layout
    final GDockPane dockPane = GUISessionProxy.getApplication().getApplicationScreen().getView().getDockPane();
    in.getWorkspaceDto().getWorkspace().setViews(UtilView.getViewsFromDockPane(dockPane));

  }

  /**
   * {@inheritDoc}
   */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "workspace.message.update.completed.body", value = "La mise à jour du workspace <b>{0}</b> est terminée."), // Force /n
                @I18nProperty(key = "workspace.message.update.completed.header", value = "Statut du workspace :"), // Force /n
  })
  public void refreshScreen(final SaveWorkspaceOut out, final WorkspaceDlgScreen screen, final StringBuilder reportMessages, final ProgressBar progressBar) {
    if (out != null) {

      // We Update the modified tree item
      final GTreeView<PerspectiveTreeItem> tree = GUISessionProxy.getPerspectiveScreen().getView().getTreeWorkspaces();
      final PerspectiveTreeItem treeItem = (PerspectiveTreeItem) tree.getSelectionModel().getSelectedItem();
      final WorkspaceItemVo workspaceItemVo = treeItem.getWorkspaceItemVo();
      workspaceItemVo.getWorkspaceDto().setWorkspace(out.getWorkspaceDto().getWorkspace());
      // To force tree item refresh
      // Tips origin : @see{https://tagmycode.com/snippet/1533/force-treeview-cell-update#.WBHzgWhxqHs}
      treeItem.setValue(null);
      treeItem.setValue(workspaceItemVo);

      // Display message Workspace saved
      final String message = I18nHelperApp.getMessage("workspace.message.update.completed.body", out.getWorkspaceDto().getWorkspace().getName());
      UtilGUIMessage.showInformationMessage("workspace.message.update.completed.header", message);
      GUISessionProxy.setCurrentWorkspaceHasChanged(false);

    }
  }

}
