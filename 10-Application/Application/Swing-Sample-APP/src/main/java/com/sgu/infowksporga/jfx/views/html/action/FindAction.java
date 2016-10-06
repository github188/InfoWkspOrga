package com.sgu.infowksporga.jfx.views.html.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.html.HtmlView;

/**
 * Description : Find text in HTML Action class<br>
 *
 * @author SGU
 */
public class FindAction extends AbstractInfoWrkspOrgaAction {

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
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "html.view.action.find.text", value = "Recherche de texte dans la vue : {0}"), // Force \n
                @I18nProperty(key = "html.view.action.find.tooltip", value = "Recherche de texte dans la vue : {0}"), // Force \n
                @I18nProperty(key = "html.view.action.find.icon", value = "/icons/find.png"), // Force \n
  })
  public FindAction(final HtmlView htmlView) {
    super("html.view.action.find");
    this.htmlView = htmlView;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    htmlView.getSearchableBar().setVisible(true);
  }

}