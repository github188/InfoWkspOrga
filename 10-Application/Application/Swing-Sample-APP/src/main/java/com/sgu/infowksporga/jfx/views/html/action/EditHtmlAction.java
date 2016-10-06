package com.sgu.infowksporga.jfx.views.html.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.html.HtmlView;

/**
 * The Class EditHtmlAction.
 * ==> Toggle button
 */
public class EditHtmlAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -6124596911162025134L;

  /** The html view. */
  private final HtmlView htmlView;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "html.view.action.edit.html.text", value = "Active/Désactive la modification du texte HTML"), // Force \n
                @I18nProperty(key = "html.view.action.edit.html.tooltip", value = "Active/Désactive la modification du texte HTML"), // Force \n
                @I18nProperty(key = "html.view.action.edit.html.icon", value = "/icons/edit.gif"), // Force \n
  //@I18nProperty(key = "html.view.action.edit.html.shortcut", value = "control H"), // Force \n
  })
  public EditHtmlAction(final HtmlView htmlView) {
    super("html.view.action.edit.html");
    this.htmlView = htmlView;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    if (htmlView.getBtnEditHTML().isSelected()) { // Show HTML Editor
      htmlView.getHtmlContentScrollPane().setVisible(false);
      htmlView.getHtmlEditorPaneScrollPane().setVisible(true);
      htmlView.getHtmlEditorPane().setText(htmlView.getHtmlContent().getText());
    }
    else if (htmlView.getBtnEditHTML().isSelected() == false) {  // Show static HTML
      htmlView.getHtmlContentScrollPane().setVisible(true);
      htmlView.getHtmlEditorPaneScrollPane().setVisible(false);
      htmlView.getHtmlContent().setText(htmlView.getHtmlEditorPane().getText());

      //--------------------------------------------------
      // Update view HTML attribute data with htmlContent
      final View viewEntity = htmlView.getViewEntity();
      final ViewAttribute htmlAttribute = viewEntity.getAttributesAsMap().get(ViewAttribute.HTML_TEXT);
      htmlAttribute.setValue(htmlView.getHtmlContent().getText());
    }
  }

}