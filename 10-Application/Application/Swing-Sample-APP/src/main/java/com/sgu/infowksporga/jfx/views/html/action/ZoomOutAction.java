package com.sgu.infowksporga.jfx.views.html.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.html.HtmlView;
import com.sgu.infowksporga.jfx.views.html.UtilZoom;

/**
 * Description : Zoom out HTML Action class<br>
 * 
 * @author SGU
 */

@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // Force \n
              @I18nProperty(key = "html.view.action.zoom.out.text", value = "Réduit la taille du HTML"), // Force \n
              @I18nProperty(key = "html.view.action.zoom.out.tooltip", value = "Réduit la taille du HTML"), // Force \n
              @I18nProperty(key = "html.view.action.zoom.out.icon", value = "/icons/zoom_out.png"), // Force \n
})
public class ZoomOutAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -6124596911162025134L;

  /**
   * The reference to get the directory tree
   */
  private final HtmlView htmlView;

  /**
   * Constructor<br>
   */
  public ZoomOutAction(final HtmlView htmlView) {
    super("html.view.action.zoom.out");
    this.htmlView = htmlView;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    UtilZoom.zoomOut(htmlView, 0.125);
  }

}