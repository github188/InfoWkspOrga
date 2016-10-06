package com.sgu.infowksporga.jfx.workspace.dlg.properties.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.workspace.dlg.properties.WorkspaceDlg;
import com.sgu.infowksporga.jfx.zfacade.remote.workspace.SaveWorkspacePropertiesFacade;

/**
 * Description : SaveWorkspaceAction class<br>
 * 
 * @author SGU
 */
public class SaveWorkspacePropertiesAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The reference to get the dialog box
   */
  private final WorkspaceDlg dialog;

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 2568554388620155754L;

  /**
   * Constructor<br>
   * 
   * @param dialog reference
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "workspace.properties.dlg.action.save.text", value = "Save"), // Force /n
                @I18nProperty(key = "workspace.properties.dlg.action.save.icon", value = "/icons/save_database.png"), // Force /n
  })
  public SaveWorkspacePropertiesAction(final WorkspaceDlg dialog) {
    super("workspace.properties.dlg.action.save");
    this.dialog = dialog;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(ActionEvent e) {
    SaveWorkspacePropertiesFacade facade = (SaveWorkspacePropertiesFacade) SpringBeanHelper.getImplementationByInterface(SaveWorkspacePropertiesFacade.class);
    GUISession.getInstance().getServiceDelegate().execute(facade, dialog);
  }

}