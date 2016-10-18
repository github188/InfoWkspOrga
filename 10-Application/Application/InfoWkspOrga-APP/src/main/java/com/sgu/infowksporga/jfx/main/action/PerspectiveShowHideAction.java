package com.sgu.infowksporga.jfx.main.action;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.jfx.action.AppBaseAction;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.main.ui.ApplicationViewFxml;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class PerspectiveShowHideAction.
 */
@Slf4j
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = PerspectiveShowHideAction.ACTION_BUNDLE_KEY + I18NConstant.TEXT, value = "Show/Hide Perspective"), // Force /n
              @I18nProperty(key = PerspectiveShowHideAction.ACTION_BUNDLE_KEY + I18NConstant.TOOLTIP_TEXT,
              value = "Afficher/Masquer le panneau gauche de la perspective"), // Force /n 
              @I18nProperty(key = PerspectiveShowHideAction.ACTION_BUNDLE_KEY + I18NConstant.ICON, value = "/icons/perspective.png"), // Force /n
              @I18nProperty(key = PerspectiveShowHideAction.ACTION_BUNDLE_KEY + I18NConstant.SHORTCUT, value = "Control+H"), // Force /n
              @I18nProperty(key = PerspectiveShowHideAction.ACTION_BUNDLE_KEY + I18NConstant.NAME, value = "ACTION_SHOW_HIDE_PERSPECTIVE"), // Force /n
})
public final class PerspectiveShowHideAction extends AppBaseAction<ActionEvent> {

  /** The Constant ACTION_PROP_BASE. */
  public static final String ACTION_BUNDLE_KEY = "application.action.toggle.button.show.hide.perspective";

  /**
   * The Constructor.
   *
   * @param control The control
   * @param bundleConfigKey The bundle config key
   */
  public PerspectiveShowHideAction(final Control control) {
    super(control, ACTION_BUNDLE_KEY, I18nHelperApp.getI18nHelper());
    // Add action to Application action manager
    GUISessionProxy.getApplication().getActionManager().addEntry(control, this);
  }

  /** {@inheritDoc} */
  @Override
  public void handle(final ActionEvent event) {
    if (event.isConsumed() == false) {
      final ApplicationViewFxml view = GUISessionProxy.getApplication().getApplicationScreen().getView();

      if (view.getTgBtnPerspectiveVisible().isSelected() == false) {
        // We have to save the real reference to be able to remove and restore Perspective view
        view.setAncPnlPerspective((AnchorPane) view.getSpnWorkspace().getItems().get(0));
        view.getSpnWorkspace().getItems().remove(view.getAncPnlPerspective());
      }
      else {
        view.getSpnWorkspace().getItems().add(0, view.getAncPnlPerspective());
        view.getSpnWorkspace().setDividerPosition(0, 0.25);
      }
    }
  }

}
