package com.sgu.infowksporga.jfx.connexion.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.connexion.ConnexionDlg;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;

/**
 * Description : CancelAction class<br>
 *
 * @author SGU
 */
public class CancelAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 2568554388620155754L;

  /**
   * Ref to the dialog
   */
  private final ConnexionDlg dialog;

  /**
   * Constructor<br>
   *
   * @param dialog Ref to the dialog box
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "connexion.dlg.action.cancel.text", value = "Cancel"), // Force /n
  })
  public CancelAction(final ConnexionDlg dialog) {
    super("connexion.dlg.action.cancel");
    this.dialog = dialog;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void actionPerformed(final ActionEvent e) {
    dialog.dispose();
    System.exit(0);
  }
}