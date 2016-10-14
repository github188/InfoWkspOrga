package com.sgu.infowksporga.jfx.perspective.action;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.jfx.action.AppBaseAction;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : RefreshPerspectiveAction class<br>
 */

@Slf4j
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = RefreshPerspectiveAction.ACTION_BUNDLE_KEY + I18NConstant.TEXT, value = "Rafraîchir"), // Force /n
              @I18nProperty(key = RefreshPerspectiveAction.ACTION_BUNDLE_KEY + I18NConstant.TOOLTIP_TEXT,
              value = "Rafraîchit la liste des perspectives & l'arbre de la perspective éventuellement sélectionnée"), // Force /n
              @I18nProperty(key = RefreshPerspectiveAction.ACTION_BUNDLE_KEY + I18NConstant.ICON, value = "/icons/refresh-yellow.png"), // Force /n
              @I18nProperty(key = RefreshPerspectiveAction.ACTION_BUNDLE_KEY + I18NConstant.NAME, value = "ACTION_PERSPECTIVE_REFRESH"), // Force /n
})
public class RefreshPerspectiveAction extends AppBaseAction<ActionEvent> {

  /** The Constant ACTION_PROP_BASE. */
  public static final String ACTION_BUNDLE_KEY = "perspective.action.button.refresh.perspective";

  /**
   * The Constructor.
   *
   * @param control The control
   * @param bundleConfigKey The bundle config key
   */
  public RefreshPerspectiveAction(final Control control) {
    super(control, ACTION_BUNDLE_KEY, I18nHelperApp.getI18nHelper());
  }

  /** {@inheritDoc} */
  @Override
  public void handle(final ActionEvent event) {
    UtilGUIMessage.showNotYetImplementedDlg();
  }

}
