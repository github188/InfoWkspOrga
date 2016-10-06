package com.sgu.infowksporga.jfx.menu.action.file;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.dialog.GMessageBox;
import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;

/**
 * Description : ExitAction class<br>
 * 
 * @author SGU
 */
public class ExitAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.file.exit.text", value = "Quitter"), // Force /n
                @I18nProperty(key = "menu.file.exit.name", value = "EXIT"), // Force /n
                @I18nProperty(key = "menu.file.exit.tooltip", value = "Confirmer la sortie de l'application"), // Force /n
                @I18nProperty(key = "menu.file.exit.mnemonic", value = "Q"), // Force /n
                @I18nProperty(key = "menu.file.exit.shortcut", value = "control Q"), // Force /n
                @I18nProperty(key = "menu.file.exit.icon", value = "/icons/door_in.png"), // Force /n
  })
  public ExitAction() {
    super("menu.file.exit");
  }

  /** {@inheritDoc} */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "exit.dlg.title", value = "Confirmation"), // Force /n
                @I18nProperty(key = "exit.dlg.message", value = "Veuillez confirmer la fermeture de l'application !"), // Force /n
  })
  public void actionPerformed(final ActionEvent e) {

    final int result = UtilDlgMessage.question("exit.dlg.title", "exit.dlg.message", GMessageBox.OK | GMessageBox.CANCEL, 380, 0);
    if (result == GMessageBox.OK) {
      doCloseOperation();
      // close application in all case
      GUISession.getInstance().getApplicationFrame().dispose();
      System.exit(0);
    }
  }

  /**
   * Description : doCloseOperation method <br>
   * This method can be overridden to add other closing operation
   */
  protected void doCloseOperation() {
    //UtilWorkspace.saveWorkspaceIfChanged();
  }

}
