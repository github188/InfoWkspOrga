package com.sgu.infowksporga.jfx.views.common.dialog.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractViewPropertiesDlg;

/**
 * Description : AbstractViewCancelAction class<br>
 *
 * @author SGU
 */
public abstract class AbstractViewCancelAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 2568554388620155754L;

  /** The dialog. */
  protected AbstractViewPropertiesDlg dialog;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "common.view.properties.dlg.action.cancel.text", value = "Cancel"), // Force /n
  })
  public AbstractViewCancelAction(final AbstractViewPropertiesDlg dialog) {
    super("common.view.properties.dlg.action.cancel");
    this.dialog = dialog;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void actionPerformed(final ActionEvent e) {
    dialog.setCancelClicked(true);
    dialog.dispose();
  }
}