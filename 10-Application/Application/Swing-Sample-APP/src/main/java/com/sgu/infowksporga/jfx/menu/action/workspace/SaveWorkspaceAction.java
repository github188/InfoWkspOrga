package com.sgu.infowksporga.jfx.menu.action.workspace;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.rules.workspace.IsWorkspaceSaveAvailableRule;
import com.sgu.infowksporga.jfx.zfacade.remote.workspace.SaveWorkspaceLayoutFacade;

/**
 * Description : SaveWorkspaceAction class<br>
 * This action is used to save the workspace layout
 * 
 * @author SGU
 */
public class SaveWorkspaceAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.workpace.save.text", value = "Save Workspace"), // Force /n
                @I18nProperty(key = "menu.workpace.save.name", value = "WORKSPACE_SAVE"), // Force /n
                @I18nProperty(key = "menu.workpace.save.tooltip",
                value = "<html>Enregistre le Workspace courant en base de données</html>"), // Force /n
                @I18nProperty(key = "menu.workpace.save.mnemonic", value = "S"), // Force /n
                @I18nProperty(key = "menu.workpace.save.shortcut", value = "control S"), // Force /n
                @I18nProperty(key = "menu.workpace.save.icon", value = "/icons/workspace-save.png"), // Force /n
  })
  public SaveWorkspaceAction() {
    super("menu.workpace.save");
    setRule(new IsWorkspaceSaveAvailableRule());
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {

    SaveWorkspaceLayoutFacade facade = (SaveWorkspaceLayoutFacade) SpringBeanHelper.getImplementationByInterface(SaveWorkspaceLayoutFacade.class);
    GUISession.getInstance().getServiceDelegate().execute(facade, null);
  }

}
