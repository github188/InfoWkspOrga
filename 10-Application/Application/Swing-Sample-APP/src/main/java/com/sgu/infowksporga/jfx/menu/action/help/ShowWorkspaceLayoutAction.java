package com.sgu.infowksporga.jfx.menu.action.help;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.rules.workspace.IsWorkspaceSelectedRule;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import net.infonode.docking.util.DeveloperUtil;

/**
 * Description : WorkspaceDisplayLayoutAction class<br>
 * This action is only used at development time to display the workspace layout
 * 
 * @author SGU
 */
public class ShowWorkspaceLayoutAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.help.show.workpace.layout.text", value = "Afficher le layout du workspace"), // Force /n
                @I18nProperty(key = "menu.help.show.workpace.layout.tooltip",
                value = "Développeur : Afficher le layout du workspace courant"), // Force /n
                @I18nProperty(key = "menu.help.show.workpace.layout.name", value = "SHOW_WORKSPACE_LAYOUT"), // Force /n
                @I18nProperty(key = "menu.help.show.workpace.layout.mnemonic", value = "w"), // Force /n
                @I18nProperty(key = "menu.help.show.workpace.layout.icon", value = "/icons/layout-for-dev.png"), // Force /n

                @I18nProperty(key = "show.workpace.layout.dlg.title", value = "Voici le layout du Workspace courant "), // Force /n
  })
  public ShowWorkspaceLayoutAction() {
    super("menu.help.show.workpace.layout");
    setRule(new IsWorkspaceSelectedRule());
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent e) {

    JFrame frame = DeveloperUtil.createWindowLayoutFrame(I18nHelperApp.getMessage("show.workpace.layout.dlg.title"),
                                                         GUISessionProxy.getCurrentWorkspace().getRootWindow());

    UtilGUI.centerWindowVsScreen(frame);
    frame.pack();

    frame.setVisible(true);

  }

}
