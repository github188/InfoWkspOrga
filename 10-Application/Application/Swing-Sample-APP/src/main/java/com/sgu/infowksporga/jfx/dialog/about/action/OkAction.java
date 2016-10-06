package com.sgu.infowksporga.jfx.dialog.about.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.dialog.about.AboutDlg;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;

/**
 * Description : OkAction class<br>
 * 
 * @author SGU
 */
public class OkAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 2568554388620165754L;

  /**
   * The reference to get the dialog box
   */
  private final AboutDlg dialog;

  /**
   * Constructor<br>
   * 
   * @param dialog reference
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "about.dlg.action.ok.text", value = "Ok"), // Force /n
  })
  public OkAction(final AboutDlg dialog) {
    super("about.dlg.action.ok");
    this.dialog = dialog;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void actionPerformed(final ActionEvent e) {
    dialog.setVisible(false);
  }
}
