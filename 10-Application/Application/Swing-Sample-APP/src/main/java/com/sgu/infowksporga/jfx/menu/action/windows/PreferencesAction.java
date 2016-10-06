package com.sgu.infowksporga.jfx.menu.action.windows;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.preferences.PreferencesDlg;

/**
 * Description : PreferencesAction class<br>
 * 
 * @author SGU
 */
public class PreferencesAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.windows.preferences.text", value = "Preferences"), // Force /n
                @I18nProperty(key = "menu.windows.preferences.name", value = "PREFERENCES"), // Force /n
                @I18nProperty(key = "menu.windows.preferences.mnemonic", value = "P"), // Force /n
                @I18nProperty(key = "menu.windows.preferences.icon", value = "/icons/preferences.png"), // Force /n
  })
  public PreferencesAction() {
    super("menu.windows.preferences");
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    final PreferencesDlg dialog = new PreferencesDlg();

    dialog.pack();
    dialog.centerDialogVsScreen();
    dialog.setVisible(true);

  }
}
