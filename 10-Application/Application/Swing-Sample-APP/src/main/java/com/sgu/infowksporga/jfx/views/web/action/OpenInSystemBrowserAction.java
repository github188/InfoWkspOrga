package com.sgu.infowksporga.jfx.views.web.action;

import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.web.WebView;

/**
 * The Class OpenInFirefoxAction.
 */
public class OpenInSystemBrowserAction extends AbstractInfoWrkspOrgaAction {

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
                @I18nProperty(key = "web.view.action.open.in.sytem.browser.text", value = "Affiche la page courante dans votre navigateur"), // Force \n
                @I18nProperty(key = "web.view.action.open.in.sytem.browser.tooltip",
                value = "Affiche la page courante dans votre navigateur"), // Force \n
                @I18nProperty(key = "web.view.action.open.in.sytem.browser.icon", value = "/icons/web/firefox.png"), // Force \n
  })
  public OpenInSystemBrowserAction(final WebView webView) {
    super("web.view.action.open.in.sytem.browser");
    this.webView = webView;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    try {
      URL currentURL = new URL(webView.getBrowserView().getBrowser().getURL());
      UtilGUI.openWebpage(currentURL);
    } catch (MalformedURLException e) {
      throw new TechnicalException(e);
    }

  }

}