package com.sgu.infowksporga.jfx.views.web.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.dto.WorkspacesViewsDto;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.web.WebView;

/**
 * The Class HomeAction.
 */
public class HomeAction extends AbstractInfoWrkspOrgaAction {

  /** The attribute serialVersionUID. */
  private static final long serialVersionUID = -6124596911162025134L;

  /** The reference to view. */
  private final WebView webView;

  /**
   * Constructor<br>.
   *
   * @param webView the web view
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "web.view.action.home.text", value = "Affiche la page initiale de la vue"), // Force \n
                @I18nProperty(key = "web.view.action.home.tooltip", value = "Affiche la page initiale de la vue"), // Force \n
                @I18nProperty(key = "web.view.action.home.icon", value = "/icons/web/home.png"), // Force \n
  })
  public HomeAction(final WebView webView) {
    super("web.view.action.home");
    this.webView = webView;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {

    WorkspacesViewsDto workspacesViewsDto = GUISessionProxy.getCurrentWorkspace().getWorkspaceDto().buildWorkspacesViewsDtoExtract();

    // For the moment we assume view are not base on a master
    View viewEntity = workspacesViewsDto.getWorkspaceViewsByName().get(webView.getViewIdentifier().getViewEntityName());

    // get URL to display
    final String webUrlStr = viewEntity.getAttributesAsMap().get(ViewAttribute.WEB_URL).getValue();
    if (UtilString.isNotBlank(webUrlStr)) {
      webView.getBrowserView().getBrowser().loadURL(webUrlStr);
    }
  }

}