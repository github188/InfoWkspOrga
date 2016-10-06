package com.sgu.infowksporga.jfx.menu.action.help;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.dialog.about.AboutDlg;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;

/**
 * Description : AboutAction class<br>
 * 
 * @author SGU
 */
public class AboutAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.help.about.text", value = "About info wrksp orga"), // Force /n
                @I18nProperty(key = "menu.help.about.name", value = "ABOUT"), // Force /n
                @I18nProperty(key = "menu.help.about.mnemonic", value = "P"), // Force /n
                @I18nProperty(key = "menu.help.about.icon", value = "/icons/information.png"), // Force /n
  })
  public AboutAction() {
    super("menu.help.about");
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    final AboutDlg dialog = new AboutDlg();

    dialog.pack();
    dialog.centerDialogVsScreen();
    dialog.setVisible(true);

  }
}
