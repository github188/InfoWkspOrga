package com.sgu.infowksporga.jfx.preferences;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.dialog.GWebDialog;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

/**
 * The Class PreferencesDialog.
 */
public class PreferencesDlg extends GWebDialog {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -5918544914235981069L;

  /**
   * The Constructor.
   *
   * @param title the title
   * @param owner the owner
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "preferences.dialog.title", value = "Preferences"), // Force /n
                @I18nProperty(key = "preferences.dialog.web.prez.url",
                value = "${prez.host.port}/${prez.app.name}/app/show-preferences.html?theme=default"), })
  public PreferencesDlg() {
    super("preferences.dialog.title", GUISessionProxy.getInfoWrkspOrgaFrame());
    //getPnlWeb().setContentURL(I18nHelperApp.getMessage("preferences.dialog.web.prez.url"));
    getPnlWeb().setContentURL("http://localhost:8080/infowrksporga-prez/app/show-preferences.html?theme=default");
  }

}
