package com.sgu.infowksporga.jfx.y_service.remote.workspace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.service.AbstractBusinessFacade;
import com.sgu.core.framework.gui.jfx.util.UtilApplication;
import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.infowksporga.business.dto.WorkspaceDto;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceIn;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceOut;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.main.mvc.ApplicationScreen;
import com.sgu.infowksporga.jfx.perspective.tree.vo.WorkspaceItemVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilView;
import com.sgu.infowksporga.jfx.util.UtilWorkspace;
import com.sgu.infowksporga.rest.RestServiceMapping;

import javafx.scene.control.ProgressBar;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : saveWorkspaceFacade class<br>
 * Used when user click on menu or toolbar button Save Workspace on the main screen application
 * Workspace properties are not modified in this case
 */
@Service
@Slf4j
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
    final WorkspaceItemVo workspaceItemVo = GUISessionProxy.getCurrentWorkspace().getWorkspaceItemVo();

    // -------- First serialize the workspace in XML
    final GDockPane dockPane = GUISessionProxy.getApplication().getApplicationScreen().getView().getDockPane();
    final String xmlDock = UtilWorkspace.buildXmlDock();
    log.debug(xmlDock);

    // -------- Secondly  rebuild Workspace informations

    // Workspace DTO to build for saving
    final WorkspaceDto workspaceDtoToSave = new WorkspaceDto();
    workspaceDtoToSave.setWorkspace(workspaceItemVo.getWorkspaceDto().getWorkspace());

    // Update workspace layout stored in memory
    final Workspace workspace = workspaceDtoToSave.getWorkspace();
    workspace.setLayout(xmlDock.getBytes());
    workspace.setWidth(dockPane.getWidth());
    workspace.setHeight(dockPane.getHeight());

    // Build Views from layout
    workspace.setViews(UtilView.getViewsFromDockPane(dockPane));

    // Prepare the pivot in to be persisted
    final SaveWorkspaceIn in = new SaveWorkspaceIn(workspaceDtoToSave);
    in.setUserInfo(GUISessionProxy.getGuiSession().getCurrentUser());

    // Call the service
    final SaveWorkspaceOut out = (SaveWorkspaceOut) UtilApplication.callRestBusinessService(RestServiceMapping.URL_SERVICE_SAVE_WORKSPACE, in, SaveWorkspaceOut.class);

    return out;
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
  public void refreshScreen(final SaveWorkspaceOut out, final ApplicationScreen screen, final StringBuilder reportMessages, final ProgressBar progressBar) {
    if (out != null) {
      // Display message Workspace saved
      final String message = I18nHelperApp.getMessage("workspace.message.update.completed.body", out.getWorkspaceDto().getWorkspace().getName());
      UtilGUIMessage.showInformationMessage("workspace.message.update.completed.header", message);

      GUISessionProxy.setCurrentWorkspaceHasChanged(false);
    }
  }

}
