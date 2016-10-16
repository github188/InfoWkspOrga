package com.sgu.infowksporga.jfx.main.action.view;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.view.web.ui.WebViewModel;
import com.sgu.infowksporga.jfx.view.web.ui.WebViewScreen;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : AddWebViewAction class<br>
 */
@Slf4j
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = AddWebViewAction.ACTION_BUNDLE_KEY + I18NConstant.TEXT, value = "Web"), // Force /n
              @I18nProperty(key = AddWebViewAction.ACTION_BUNDLE_KEY + I18NConstant.TOOLTIP_TEXT, value = "<html>Ajoute une vue de type : <b>Web</b></html>"), // Force /n 
              @I18nProperty(key = AddWebViewAction.ACTION_BUNDLE_KEY + I18NConstant.ICON, value = "/icons/web.png"), // Force /n
              @I18nProperty(key = AddWebViewAction.ACTION_BUNDLE_KEY + I18NConstant.MNEMONIC, value = "W"), // Force /n
              @I18nProperty(key = AddWebViewAction.ACTION_BUNDLE_KEY + I18NConstant.SHORTCUT, value = "Control+W"), // Force /n
              @I18nProperty(key = AddWebViewAction.ACTION_BUNDLE_KEY + I18NConstant.NAME, value = "ACTION_ADD_WEB_VIEW"), // Force /n
})
public class AddWebViewAction extends AViewAddAction {

  /** The Constant ACTION_PROP_BASE. */
  public static final String ACTION_BUNDLE_KEY = "application.action.add.web.view";

  /**
   * The Constructor.
   *
   * @param control The control
   * @param bundleConfigKey The bundle config key
   */
  public AddWebViewAction(final Control control) {
    super(control, ACTION_BUNDLE_KEY, I18nHelperApp.getI18nHelper());
    // Add action to Application action manager
    GUISessionProxy.getCurrentApplication().getActionManager().addEntry(control, this);
  }

  /** {@inheritDoc} */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "application.web.view.default.text", value = "Vue par défaut (Quand le workspace sélectionné n'est encore définit)"), // Force /n
                @I18nProperty(key = "application.web.view.default.url", value = "https://www.google.fr"), // Force /n
                @I18nProperty(key = "application.web.view.default.icon", value = "/icons/web.png"), // Force /n
  })
  public void handle(final ActionEvent event) {
    final WebViewModel model = new WebViewModel();
    model.addParameter(WebViewModel.VIEW_TITLE, "application.web.view.default.text");
    model.addParameter(WebViewModel.VIEW_ICON, "application.web.view.default.icon");
    model.addParameter(WebViewModel.VIEW_SCREEN_BEAN, WebViewScreen.class.getName());
    model.addParameter(WebViewModel.WEB_VIEW_URL, "application.web.view.default.url");

    addDefaultView(model);

  }

}
