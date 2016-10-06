package com.sgu.infowksporga.jfx.zfacade.remote.workspace;

import java.util.ArrayList;

import javax.swing.ProgressMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.util.UtilNotificationMsg;
import com.sgu.infowksporga.business.dto.WorkspaceDto;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceIn;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceOut;
import com.sgu.infowksporga.jfx.InfoWrkspOrgaFrame;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.util.UtilWorkspace;
import com.sgu.infowksporga.jfx.views.AbstractView;
import com.sgu.infowksporga.jfx.views.AbstractViewIdentifier;
import com.sgu.infowksporga.jfx.views.ViewDefault;
import com.sgu.infowksporga.jfx.zfacade.AbstractBusinessFacade;
import com.sgu.infowksporga.rest.RestServiceMapping;

import net.infonode.docking.DockingWindow;
import net.infonode.docking.RootWindow;
import net.infonode.docking.internalutil.InternalDockingUtil;

/**
 * Description : saveWorkspaceFacade class<br>
 */
@Service
public class SaveWorkspaceLayoutFacade extends AbstractBusinessFacade<SaveWorkspaceOut, InfoWrkspOrgaFrame> {

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
  public SaveWorkspaceOut execute(final InfoWrkspOrgaFrame container) throws TechnicalException, BusinessException {
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
    final SaveWorkspaceOut out = (SaveWorkspaceOut) UtilInfoWrkspOrga.callRestBusinessService(RestServiceMapping.URL_SERVICE_SAVE_WORKSPACE,
                                                                                             in, SaveWorkspaceOut.class);

    return out;
  }

  /**
   * Gets the all views excepted default image view.
   *
   * @return the all views excepted default image view
   */
  private ArrayList<DockingWindow> getAllViewsExceptedDefaultImageView() {
    final ArrayList<DockingWindow> views = new ArrayList<DockingWindow>(10);
    final RootWindow rootWindow = GUISessionProxy.getCurrentWorkspace().getRootWindow();
    InternalDockingUtil.getViews(rootWindow, views);

    // Find the default view and close it before saving
    for (final DockingWindow view : views) {
      if (view instanceof ViewDefault) {
        LOGGER.debug("View removed : {}", view);
        view.close();
        rootWindow.removeView((net.infonode.docking.View) view);
        break;
      }
    }
    return views;
  }

  /**
   * Adds the view info.
   *
   * @param view the view
   * @param workspaceDto the workspace dto
   */
  private void addViewInformations(final AbstractView view, final WorkspaceDto workspaceDto) {
    final AbstractViewIdentifier config = view.getViewIdentifier();

    final String viewTitle = config.getViewEntityName();
    final String viewTypeParam = config.getClass().getName();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "workspace.saving.completed", value = "La sauvegarde du workspace <b>{0}</b> est terminée."), // Force /n
  })
  public void refreshScreen(final SaveWorkspaceOut out, final InfoWrkspOrgaFrame container, final StringBuilder reportMessages,
  final ProgressMonitor monitor) {
    if (out != null) {
      // Display message Workspace saved
      final String message = I18nHelperApp.getMessage("workspace.saving.completed", out.getWorkspaceDto().getWorkspace().getName());
      UtilNotificationMsg.displayMessage(message);

      GUISessionProxy.setCurrentWorkspaceHasChanged(false);
    }
  }
}
