package com.sgu.infowksporga.jfx.y_service.remote.workspace.cu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.tree.GTreeView;
import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.infowksporga.business.dto.WorkspaceDto;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceIn;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceOut;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTreeItem;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.workspace.dlg.mvc.WorkspaceDlgScreen;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : CreateWorkspaceFacade class<br>
 */
@Service
@Slf4j
public class CreateWorkspaceFacade extends AbstractSavingWorkspaceFacade {

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CreateWorkspaceFacade.class);

  /** The new tree item. */
  private PerspectiveTreeItem newTreeItem;

  /**
   * Constructor<br>
   */
  public CreateWorkspaceFacade() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected String buildXmlDock() {
    return "Default";
  }

  /** {@inheritDoc} */
  @Override
  protected void addNewWorkspaceItemToParentTreeNode(final GTreeView<PerspectiveTreeItem> tree, final Workspace workspace, final WorkspaceDto workspaceDtoToSave) {
    // Add Workspace to Tree to facilitate the new Perspective workspace order calculation
    final PerspectiveTreeItem treeItem = (PerspectiveTreeItem) tree.getSelectionModel().getSelectedItem();
    // if the order of this workspace is not defined we use by default the last index of the child parent.
    if (workspace.getOrder() == null || workspace.getOrder() > treeItem.getChildren().size()) {
      workspace.setOrder(treeItem.getChildren().size()); // used only here to add new workspace at the correct parent index. The order is re-computed from the root node
    }
    // workspace.getOrder() ==> The child position in his parent
    treeItem.getChildren().add(workspace.getOrder(), new PerspectiveTreeItem(workspaceDtoToSave));
    if (treeItem.isExpanded() == false) {
      treeItem.setExpanded(true);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void finalizePivotInDataBeforeCallingService(final SaveWorkspaceIn in) {
    in.getWorkspaceDto().getWorkspace().setId(Workspace.I_AM_A_NEW_WORKSPACE); // set to a string value because we need to know which workspace need the creation of a new Id
    // Used in map <String, Integer>  <WkspId, Int>
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "workspace.message.creation.completed.body",
                value = "La Création du workspace <b>{0}</b> est terminée avec l'id : {1}. "
                        + "Celui-ci a été ajouté au noeud {2} de l''arbre présentant la perspective selectionnée "), // Force /n
                @I18nProperty(key = "workspace.message.creation.completed.header", value = "Statut du workspace :"), // Force /n
  })
  public void refreshScreen(final SaveWorkspaceOut out, final WorkspaceDlgScreen screen, final StringBuilder reportMessages, final ProgressBar progressBar) {
    if (out != null) {
      // Display message Workspace saved
      final String message = I18nHelperApp.getMessage("workspace.message.creation.completed.body", out.getWorkspaceDto().getWorkspace().getName(),
                                                      out.getWorkspaceDto().getWorkspace().getId());
      UtilGUIMessage.showInformationMessage("workspace.message.creation.completed.header", message);
      GUISessionProxy.setCurrentWorkspaceHasChanged(false);

      // We select the new created item
      Platform.runLater(() -> {
        final GTreeView<PerspectiveTreeItem> tree = GUISessionProxy.getPerspectiveScreen().getView().getTreeWorkspaces();
        tree.getSelectionModel().select(newTreeItem);
      });

    }
  }

}
