package com.sgu.infowksporga.jfx.perspective.action;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.jfx.action.AppBaseAction;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class ClearFilterAction.
 */
@Slf4j
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = ClearWorkspacesFilterAction.ACTION_BUNDLE_KEY + I18NConstant.TEXT, value = "Efface le filtre"), // Force /n
              @I18nProperty(key = ClearWorkspacesFilterAction.ACTION_BUNDLE_KEY + I18NConstant.TOOLTIP_TEXT,
              value = "Efface le filtre et ré-affiche l'ensemble des workspaces de la perspective "), // Force /n 
              @I18nProperty(key = ClearWorkspacesFilterAction.ACTION_BUNDLE_KEY + I18NConstant.ICON, value = "/icons/clear-filter.png"), // Force /n
              @I18nProperty(key = ClearWorkspacesFilterAction.ACTION_BUNDLE_KEY + I18NConstant.NAME, value = "ACTION_PERSPECTIVE_CLEAR_FILTER"), // Force /n
})
public class ClearWorkspacesFilterAction extends AppBaseAction<ActionEvent> {

  /** The Constant ACTION_PROP_BASE. */
  public static final String ACTION_BUNDLE_KEY = "perspective.action.button.clear.filter";

  /**
   * The Constructor.
   *
   * @param control The control
   * @param bundleConfigKey The bundle config key
   */
  public ClearWorkspacesFilterAction(final Control control) {
    super(control, ACTION_BUNDLE_KEY, I18nHelperApp.getI18nHelper());
  }

  /** {@inheritDoc} */
  @Override
  public void handle(final ActionEvent event) {
    GUISessionProxy.getPerspectiveScreen().controller().clearWorkspacesFilter();
  }

}
