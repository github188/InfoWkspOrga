package com.sgu.infowksporga.jfx.views.web.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.web.WebView;

/**
 * The Class ForwardAction.
 */
public class ForwardAction extends AbstractInfoWrkspOrgaAction {

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
                @I18nProperty(key = "web.view.action.forward.text", value = "Avance à la page suivante"), // Force \n
                @I18nProperty(key = "web.view.action.forward.tooltip", value = "Avance à la page suivante"), // Force \n
                @I18nProperty(key = "web.view.action.forward.icon", value = "/icons/web/forward.png"), // Force \n
  })
  public ForwardAction(final WebView webView) {
    super("web.view.action.forward");
    this.webView = webView;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    webView.getBrowserView().getBrowser().goForward();
  }

}