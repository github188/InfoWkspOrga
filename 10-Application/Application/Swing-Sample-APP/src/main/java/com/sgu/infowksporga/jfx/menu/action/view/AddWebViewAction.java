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
import com.sgu.infowksporga.jfx.views.web.WebView;
import com.sgu.infowksporga.jfx.views.web.WebViewIdentifier;

/**
 * Description : AddHtmlViewAction class<br>
 * 
 * @author SGU
 */
public class AddWebViewAction extends AbstractAddViewAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.workspace.views.web.text", value = "Web"), // Force /n
                @I18nProperty(key = "menu.workspace.views.web.name", value = "ADD_WEB_VIEW"), // Force /n
                @I18nProperty(key = "menu.workspace.views.web.tooltip", value = "<html>Ajoute une vue de type : <b>Web</b></html>"), // Force /n
                @I18nProperty(key = "menu.workspace.views.web.mnemonic", value = "W"), // Force /n
                @I18nProperty(key = "menu.workspace.views.web.shortcut", value = "control W"), // Force /n
                @I18nProperty(key = "menu.workspace.views.web.icon", value = "/icons/web.png"), // Force /n
  })
  public AddWebViewAction() {
    super("menu.workspace.views.web");
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
    view.setJavaType(WebViewIdentifier.class.getName());
    view.setName("My Web site");

    ViewAttribute attribute = new ViewAttribute();
    attribute.setName(ViewAttribute.WEB_URL);
    attribute.setValue("http://localhost:8080/infowrksporga-prez/?theme=black");
    view.addAttribute(attribute);

    getWorkspaceDto().getWorkspace().addView(view);

    WebViewIdentifier viewIdentifier = new WebViewIdentifier(null, view.getName());
    final WebView newViewUI = new WebView(viewIdentifier);

    GUISessionProxy.setCurrentWorkspaceHasChanged(true);

    /*
     * final FileExplorerViewPropertiesDlg dlg = new FileExplorerViewPropertiesDlg(newView);
     * dlg.pack();
     * dlg.centerDialogVsScreen();
     * dlg.setVisible(true);
     * if (dlg.isCancelClicked()) {
     * return;
     * }
     */

    UtilWorkspace.addNewViewToWorkspace(newViewUI);

  }
}
