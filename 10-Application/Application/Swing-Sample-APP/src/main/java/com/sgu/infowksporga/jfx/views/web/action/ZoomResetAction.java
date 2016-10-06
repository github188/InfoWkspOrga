package com.sgu.infowksporga.jfx.views.web.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.web.WebView;

/**
 * Description : Zoom reset on HTML Action class<br>
 *
 * @author SGU
 */
public class ZoomResetAction extends AbstractInfoWrkspOrgaAction {

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
                @I18nProperty(key = "web.view.action.zoom.reset.text", value = "Repositionne la taille du HTML à 100 %"), // Force \n
                @I18nProperty(key = "web.view.action.zoom.reset.tooltip", value = "Repositionne la taille du HTML à 100 %"), // Force \n
                @I18nProperty(key = "web.view.action.zoom.reset.icon", value = "/icons/web/zoom_reset.png"), // Force \n
  })
  public ZoomResetAction(final WebView webView) {
    super("web.view.action.zoom.reset");
    this.webView = webView;
  }

  /** {@resetheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    webView.getBrowserView().getBrowser().zoomReset();
  }

}