package com.sgu.infowksporga.jfx.menu.action.view;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;
import com.sgu.infowksporga.jfx.rules.workspace.IsWorkspaceSelectedRule;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilWorkspace;
import com.sgu.infowksporga.jfx.views.html.HtmlView;
import com.sgu.infowksporga.jfx.views.html.HtmlViewIdentifier;

/**
 * Description : AddHtmlViewAction class<br>
 *
 * @author SGU
 */
public class AddHtmlViewAction extends AbstractAddViewAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.workspace.views.html.text", value = "HTML"), // Force /n
                @I18nProperty(key = "menu.workspace.views.html.name", value = "ADD_HTML_VIEW"), // Force /n
                @I18nProperty(key = "menu.workspace.views.html.tooltip", value = "<html>Ajoute une vue de type : <b>HTML</b></html>"), // Force /n
                @I18nProperty(key = "menu.workspace.views.html.mnemonic", value = "H"), // Force /n
                @I18nProperty(key = "menu.workspace.views.html.shortcut", value = "control H"), // Force /n
                @I18nProperty(key = "menu.workspace.views.html.icon", value = "/icons/html.png"), // Force /n
  })
  public AddHtmlViewAction() {
    super("menu.workspace.views.html");
    setRule(new IsWorkspaceSelectedRule());
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {

    View view = new View();
    view.setOwner(GUISessionProxy.getGuiSession().getCurrentUser());
    view.setCategory("Default");
    view.setPartage(PartageEnum.PUBLIC);
    view.setWorkspaceId(getWorkspaceDto().getWorkspace().getId());
    view.setJavaType(HtmlViewIdentifier.class.getName());
    view.setName("Perso");

    ViewAttribute attributeText = new ViewAttribute();
    attributeText.setName(ViewAttribute.HTML_TEXT);
    attributeText.setValue("<HTML><BODY><h1>Hello World !!</h1></BODY></HTML>");
    view.addAttribute(attributeText);

    ViewAttribute attributeZoom = new ViewAttribute();
    attributeZoom.setName(ViewAttribute.HTML_ZOOM);
    attributeZoom.setValue("0");
    view.addAttribute(attributeZoom);

    getWorkspaceDto().getWorkspace().addView(view);

    HtmlViewIdentifier viewIdentifier = new HtmlViewIdentifier(null, view.getName());
    final HtmlView newViewUI = new HtmlView(viewIdentifier);

    GUISessionProxy.setCurrentWorkspaceHasChanged(true);

    UtilWorkspace.addNewViewToWorkspace(newViewUI);

  }
}
