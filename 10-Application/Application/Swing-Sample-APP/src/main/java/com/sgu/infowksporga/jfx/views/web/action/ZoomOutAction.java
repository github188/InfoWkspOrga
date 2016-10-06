package com.sgu.infowksporga.jfx.views.web.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.web.WebView;

/**
 * Description : Zoom out HTML Action class<br>
 *
 * @author SGU
 */

@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // Force \n
              @I18nProperty(key = "web.view.action.zoom.out.text", value = "Réduit la taille du HTML"), // Force \n
              @I18nProperty(key = "web.view.action.zoom.out.tooltip", value = "Réduit la taille du HTML"), // Force \n
              @I18nProperty(key = "web.view.action.zoom.out.icon", value = "/icons/web/zoom_out.png"), // Force \n
})
public class ZoomOutAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -6124596911162025134L;

  /**
   * The reference to get the directory tree
   */
  private final WebView webView;

  /**
   * Constructor<br>
   */
  public ZoomOutAction(final WebView webView) {
    super("web.view.action.zoom.out");
    this.webView = webView;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    webView.getBrowserView().getBrowser().zoomOut();
  }

}