package com.sgu.infowksporga.jfx.views.web.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.web.WebView;

/**
 * The Class BackwardAction.
 */
public class BackwardAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -6124596911162025134L;

  /**
   * The reference to view
   */
  private final WebView webView;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "web.view.action.back.text", value = "Retourne à la page précédente"), // Force \n
                @I18nProperty(key = "web.view.action.back.tooltip", value = "Retourne à la page précédente"), // Force \n
                @I18nProperty(key = "web.view.action.back.icon", value = "/icons/web/back.png"), // Force \n
  })
  public BackwardAction(final WebView webView) {
    super("web.view.action.back");
    this.webView = webView;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    webView.getBrowserView().getBrowser().goBack();
  }

}