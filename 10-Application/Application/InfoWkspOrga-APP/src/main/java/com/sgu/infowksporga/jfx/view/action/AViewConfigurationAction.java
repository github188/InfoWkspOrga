package com.sgu.infowksporga.jfx.view.action;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.jfx.action.AppBaseAction;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.view.web.action.WebViewConfigurationAction;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = WebViewConfigurationAction.ACTION_BUNDLE_KEY + I18NConstant.TEXT, value = ""), // Force /n
              @I18nProperty(key = WebViewConfigurationAction.ACTION_BUNDLE_KEY + I18NConstant.TOOLTIP_TEXT, value = "Configurer la vue"), // Force /n
              @I18nProperty(key = WebViewConfigurationAction.ACTION_BUNDLE_KEY + I18NConstant.ICON, value = "/icons/configuration.gif"), // Force /n
})
public abstract class AViewConfigurationAction extends AppBaseAction<ActionEvent> {

  /** The Constant ACTION_PROP_BASE. */
  public static final String ACTION_BUNDLE_KEY = "application.action.view.button.configuration";

  /**
   * The Constructor.
   *
   * @param control The control
   */
  public AViewConfigurationAction(final Control control) {
    super(control, ACTION_BUNDLE_KEY, I18nHelperApp.getI18nHelper());
    // Add action to Application action manager
    GUISessionProxy.getApplication().getActionManager().addEntry(control, this);
  }

  /** {@inheritDoc} */
  @Override
  public void handle(final ActionEvent event) {
    UtilGUIMessage.showNotYetImplementedDlg();
  }
}
