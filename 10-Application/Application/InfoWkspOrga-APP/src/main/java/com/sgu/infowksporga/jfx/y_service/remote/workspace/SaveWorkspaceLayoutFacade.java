package com.sgu.infowksporga.jfx.y_service.remote.workspace;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.service.AbstractBusinessFacade;
import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.infowksporga.business.dto.WorkspaceDto;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceIn;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceOut;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.main.ui.ApplicationScreen;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.rest.RestServiceMapping;

import javafx.scene.control.ProgressBar;

/**
 * Description : saveWorkspaceFacade class<br>
 */
@Service
public class SaveWorkspaceLayoutFacade extends AbstractBusinessFacade<SaveWorkspaceOut, ApplicationScreen> {

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SaveWorkspaceLayoutFacade.class);

  /**
   * Constructor<br>
   */
  public SaveWorkspaceLayoutFacade() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SaveWorkspaceOut execute(final ApplicationScreen screen) throws TechnicalException, BusinessException {
    // Get current workspace
    final Workspace workspace = GUISessionProxy.getCurrentWorkspace().getWorkspaceDto().getWorkspace();

    // -------- First get all views and Remove the default view if user don't have done it before
    final ArrayList<DockingWindow> views = getAllViewsExceptedDefaultImageView();

    // -------- Secondly  rebuild Workspace informations

    // Workspace DTO to build for saving
    final WorkspaceDto workspaceDtoToSave = new WorkspaceDto();
    workspaceDtoToSave.setWorkspace(workspace);

    // Update workspace layout
    final byte[] layout = UtilWorkspace.getCurrentWorkspaceLayout();
    workspaceDtoToSave.getWorkspace().setLayout(layout);

    // Prepare the pivot in to be persisted
    final SaveWorkspaceIn in = new SaveWorkspaceIn(workspaceDtoToSave);
    in.setUserLogin(GUISessionProxy.getGuiSession().getCurrentUser());

    // Call the service
    final SaveWorkspaceOut out = (SaveWorkspaceOut) UtilMazeExplorer.callRestBusinessService(RestServiceMapping.URL_SERVICE_SAVE_WORKSPACE, in, SaveWorkspaceOut.class);

    return out;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "workspace.message.saving.completed.body", value = "La sauvegarde du workspace <b>{0}</b> est terminée."), // Force /n
                @I18nProperty(key = "workspace.message.saving.completed.header", value = "Statut du workspace :"), // Force /n
  })
  public void refreshScreen(final SaveWorkspaceOut out, final ApplicationScreen screen, final StringBuilder reportMessages, final ProgressBar progressBar) {
    if (out != null) {
      // Display message Workspace saved
      final String message = I18nHelperApp.getMessage("workspace.message.saving.completed.body", out.getWorkspaceDto().getWorkspace().getName());
      UtilGUIMessage.showInformationMessage("workspace.message.saving.completed.header", message);

      GUISessionProxy.setCurrentWorkspaceHasChanged(false);
    }
  }
}
