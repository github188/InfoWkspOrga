package com.sgu.infowksporga.jfx.main.action.view;

import org.dockfx.DockPos;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockNode;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.enumeration.DockPosEnum;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilView;
import com.sgu.infowksporga.jfx.view.web.ui.WebViewModel;
import com.sgu.infowksporga.jfx.view.web.ui.WebViewScreen;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : AddWebViewAction class<br>
 */
@Slf4j
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = AddWebViewAction.ACTION_BUNDLE_KEY + I18NConstant.TEXT, value = "Web"), // Force /n
              @I18nProperty(key = AddWebViewAction.ACTION_BUNDLE_KEY + I18NConstant.TOOLTIP_TEXT, value = "<html>Ajoute une vue de type : <b>Web</b></html>"), // Force /n 
              @I18nProperty(key = AddWebViewAction.ACTION_BUNDLE_KEY + I18NConstant.ICON, value = "/icons/web.png"), // Force /n
              @I18nProperty(key = AddWebViewAction.ACTION_BUNDLE_KEY + I18NConstant.MNEMONIC, value = "W"), // Force /n
              @I18nProperty(key = AddWebViewAction.ACTION_BUNDLE_KEY + I18NConstant.SHORTCUT, value = "Control+W"), // Force /n
              @I18nProperty(key = AddWebViewAction.ACTION_BUNDLE_KEY + I18NConstant.NAME, value = "ACTION_ADD_WEB_VIEW"), // Force /n
})
public class AddWebViewAction extends AViewAddAction {

  /** The Constant ACTION_PROP_BASE. */
  public static final String ACTION_BUNDLE_KEY = "application.action.add.web.view";

  /**
   * The Constructor.
   *
   * @param control The control
   * @param bundleConfigKey The bundle config key
   */
  public AddWebViewAction(final Control control) {
    super(control, ACTION_BUNDLE_KEY, I18nHelperApp.getI18nHelper());
    // Add action to Application action manager
    GUISessionProxy.getApplication().getActionManager().addEntry(control, this);
  }

  /** {@inheritDoc} */
  @Override

  public void handle(final ActionEvent event) {

    // TODO show View Configuration instead direct creation
    final WebViewModel model = new WebViewModel();
    final View view = new View();
    view.setName("application.web.view.default.text");
    view.setIcon("application.web.view.default.icon");
    view.setScreenBean(WebViewScreen.class.getName());
    view.setModelBean(WebViewModel.class.getName());
    view.setDockNodeBean(GDockNode.class.getName());
    view.setDockPos(DockPosEnum.getEnumForValue(DockPos.RIGHT.toString()));
    view.addAttribute(null, null, WebViewModel.WEB_VIEW_URL, "application.web.view.default.url");

    model.setEntityView(view);

    final DockPosEnum docPosEnum = model.getEntityView().getDockPos();
    UtilView.addDockPaneView(model, DockPos.valueOf(docPosEnum.getValue()), null);

  }

}
