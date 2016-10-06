package com.sgu.infowksporga.jfx.menu.action.file;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.rules.IsAtLeastViewFileSelectedRule;

/**
 * Description : SendFileByMailAction class<br>
 * Send all workspace selected file by mail
 * 
 * @author SGU
 */
public class SendFileExplorerViewFilesByMailAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.file.send.files.text", value = "Send files"), // Force /n
                @I18nProperty(key = "menu.file.send.files.name", value = "SEND_SELECTED_FILES_BY_MAIL"), // Force /n
                @I18nProperty(key = "menu.file.send.files.tooltip",
                value = "Envoie les fichiers sélectionnés toutes vues (même non visible)"), // Force /n
                @I18nProperty(key = "menu.file.send.files.mnemonic", value = "d"), // Force /n
                @I18nProperty(key = "menu.file.send.files.shortcut", value = "control A"), // [A]ir mail -- Force /n
                @I18nProperty(key = "menu.file.send.files.icon", value = "/icons/mail-send.png"), // Force /n
  })
  public SendFileExplorerViewFilesByMailAction() {
    super("menu.file.send.files");
    setRule(new IsAtLeastViewFileSelectedRule(null));
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    //final Desktop desktop = Desktop.getDesktop();
    //final String message = "mailto:username@domain.com?subject=New_Profile&body=yyyyyyy";

    UtilGUI.showNotYetImplementedDlg();

    /*
     * try {
     * desktop.mail(new URI(message));
     * } catch (final Exception e) {
     * throw new TechnicalException(e);
     * }
     */
  }
}
