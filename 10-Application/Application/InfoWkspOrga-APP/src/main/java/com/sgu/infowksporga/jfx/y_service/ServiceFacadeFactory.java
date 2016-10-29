package com.sgu.infowksporga.jfx.y_service;

import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.main.mvc.ApplicationScreen;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.workspace.dlg.mvc.WorkspaceDlgScreen;
import com.sgu.infowksporga.jfx.y_service.remote.workspace.SaveWorkspaceLayoutFacade;
import com.sgu.infowksporga.jfx.y_service.remote.workspace.cu.CreateWorkspaceFacade;
import com.sgu.infowksporga.jfx.y_service.remote.workspace.cu.UpdateWorkspaceFacade;

/**
 * The Class ServiceFacadeFactory.
 */
public final class ServiceFacadeFactory {

  /**
   * The Constructor.
   */
  private ServiceFacadeFactory() {
  }

  /**
   * Call Create Workspace service in Database
   */
  public static final void callWorkspaceCreateService(final WorkspaceDlgScreen screen) {
    final CreateWorkspaceFacade facade = SpringBeanHelper.getImplementationByInterface(CreateWorkspaceFacade.class);
    GUISessionProxy.getGuiSession().getServiceDelegate().execute(facade, screen);
  }

  /**
   * Call Update Workspace service in Database
   */
  public static final void callWorkspaceUpdateService(final WorkspaceDlgScreen screen) {
    final UpdateWorkspaceFacade facade = SpringBeanHelper.getImplementationByInterface(UpdateWorkspaceFacade.class);
    GUISessionProxy.getGuiSession().getServiceDelegate().execute(facade, screen);
  }

  /**
   * Call save workspace layout service in Database.
   */
  public static final void callWorkspaceLayoutSaveService(final ApplicationScreen applicationScreen) {
    final SaveWorkspaceLayoutFacade facade = SpringBeanHelper.getImplementationByInterface(SaveWorkspaceLayoutFacade.class);
    GUISessionProxy.getGuiSession().getServiceDelegate().execute(facade, applicationScreen);
  }

}
