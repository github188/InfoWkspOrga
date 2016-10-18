package com.sgu.infowksporga.jfx.main.action;

import java.util.Optional;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.jfx.action.AppBaseAction;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class ExitAction.
 */
@Slf4j
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = ExitAction.ACTION_BUNDLE_KEY + I18NConstant.TEXT, value = ""), // Force /n
              @I18nProperty(key = ExitAction.ACTION_BUNDLE_KEY + I18NConstant.TOOLTIP_TEXT, value = "Fermer l'application"), // Force /n 
              @I18nProperty(key = ExitAction.ACTION_BUNDLE_KEY + I18NConstant.ICON, value = "/icons/exit.png"), // Force /n
              @I18nProperty(key = ExitAction.ACTION_BUNDLE_KEY + I18NConstant.SHORTCUT, value = "Control+X"), // Force /n
              @I18nProperty(key = ExitAction.ACTION_BUNDLE_KEY + I18NConstant.NAME, value = "ACTION_EXIT"), // Force /n
})
public class ExitAction extends AppBaseAction<ActionEvent> {

  /** The Constant ACTION_PROP_BASE. */
  public static final String ACTION_BUNDLE_KEY = "application.action.button.exit";

  /**
   * The Constructor.
   *
   * @param control The control
   * @param bundleConfigKey The bundle config key
   */
  public ExitAction(final Control control) {
    super(control, ACTION_BUNDLE_KEY, I18nHelperApp.getI18nHelper());
    // Add action to Application action manager
    GUISessionProxy.getApplication().getActionManager().addEntry(control, this);
  }

  /** {@inheritDoc} */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "application.action.confirm.exit.header", value = "Exit Application"), // Force /n
                @I18nProperty(key = "application.action.confirm.exit.body", value = "Are you sure you want to exit?"), // Force /n 
  })
  public void handle(final ActionEvent event) {
    final Optional<ButtonType> result = UtilGUIMessage.showConfirmMessage("application.action.confirm.exit.header", "application.action.confirm.exit.body");

    if (result.isPresent() && result.get() == ButtonType.OK) {
      Platform.exit();
      System.exit(0);
    }
  }

}
