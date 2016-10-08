package com.sgu.infowksporga.jfx.util;

import com.sgu.core.framework.gui.jfx.i18n.I18nHelperJavaFX;
import com.sgu.core.framework.gui.jfx.util.UtilGUI;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.core.framework.util.UtilString;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Class UtilControl.
 */
public final class UtilControl {

  /**
   * The Constructor.
   */
  private UtilControl() {
    // Utility class
  }

  /**
   * Builds the simple button.
   *
   * @param bundleConfigKey the bundle config key
   * @return the button
   */
  public static Button buildSimpleButton(final String bundleConfigKey) {
    final Button button = new Button();
    applyBundleConfigToButton(bundleConfigKey, button);
    return button;
  }

  /**
   * Apply bundle config to button.
   *
   * @param bundleConfigKey the bundle config key
   * @param button the button
   */
  public static void applyBundleConfigToButton(final String bundleConfigKey, final Button button) {
    String text = I18nHelperJavaFX.getNullMessage(new StringBuilder(bundleConfigKey).append(I18NConstant.TEXT).toString());
    button.setText(text);

    //Define the attributes of the control depend of property define in a property file

    final String toolTip = I18nHelperJavaFX.getNullMessage(new StringBuilder(bundleConfigKey).append(I18NConstant.TOOLTIP_TEXT).toString());
    if (toolTip != null) {
      button.setTooltip(new Tooltip(toolTip));
    }

    final String mnemonic = I18nHelperJavaFX.getNullMessage(new StringBuilder(bundleConfigKey).append(I18NConstant.MNEMONIC).toString());
    if (UtilString.isNotBlank(mnemonic)) {
      button.setMnemonicParsing(true);
      text = UtilString.replaceOnce(text, "" + mnemonic.charAt(0), "_" + mnemonic.charAt(0));
      button.setText(text);
    }

    final String imagePath = I18nHelperJavaFX.getNullMessage(new StringBuilder(bundleConfigKey).append(I18NConstant.ICON).toString());
    if (UtilString.isNotBlank(imagePath)) {
      final Image icon = UtilGUI.getImage(imagePath);
      button.setGraphic(new ImageView(icon));
    }

    final String name = I18nHelperJavaFX.getNullMessage(new StringBuilder(bundleConfigKey).append(I18NConstant.NAME).toString());
    if (UtilString.isNotBlank(name)) {
      button.setId(name);
    }

    /*
     * final String shorcut = I18nHelperJavaFX.getNullMessage(new StringBuilder(bundleConfigKey).append(I18NConstant.SHORTCUT).toString());
     * if (UtilString.isNotBlank(shorcut)) {
     * final KeyCombination keyCombination = KeyCombination.valueOf(shorcut);
     * button.getScene().getAccelerators().
     * }
     */

  }

}
