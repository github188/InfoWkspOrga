package com.sgu.infowksporga.jfx.dialog.zip.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.dialog.zip.ZipSelectedFilesDlg;
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
   * The reference to get the dialog box
   */
  private final ZipSelectedFilesDlg dialog;

  /**
   * Constructor<br>
   * 
   * @param dialog reference
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "zip.selected.files.dlg.action.cancel.text", value = "Annuler"), // Force /n
  })
  public CancelAction(final ZipSelectedFilesDlg dialog) {
    super("zip.selected.files.dlg.action.cancel");
    this.dialog = dialog;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void actionPerformed(final ActionEvent e) {

    if (dialog.getZipThread() != null) {
      dialog.getZipThread().interrupt();
    }

    dialog.setVisible(false);
  }
}
