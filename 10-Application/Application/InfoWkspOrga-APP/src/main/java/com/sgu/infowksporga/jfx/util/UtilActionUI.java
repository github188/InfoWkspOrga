package com.sgu.infowksporga.jfx.util;

import com.sgu.core.framework.gui.jfx.i18n.I18nHelperJavaFX;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.core.framework.util.UtilString;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

/**
 * The Class UtilActionUI.
 */
public final class UtilActionUI {

  private UtilActionUI() {
    // Utility class
  }

  public static Button buildSimpleButton(final String bundleConfigKey) {
    final Button button = new Button();
    applyBundleConfigToButton(bundleConfigKey, button);
    return button;
  }

  public static void applyBundleConfigToButton(final String bundleConfigKey, final Button button) {
    String text = I18nHelperJavaFX.getNullMessage(new StringBuilder(bundleConfigKey).append(I18NConstant.TEXT).toString());
    button.setText(text);

    //Define the attribute of the menu depend of property define in a property file
    if (text != null) {
      final String toolTip = I18nHelperJavaFX.getNullMessage(new StringBuilder(text).append(I18NConstant.TOOLTIP_TEXT).toString());
      if (toolTip != null) {
        button.setTooltip(new Tooltip(toolTip));
      }

      final String mnemonic = I18nHelperJavaFX.getNullMessage(new StringBuilder(text).append(I18NConstant.MNEMONIC).toString());
      if (mnemonic != null && mnemonic.length() > 0) {
        button.setMnemonicParsing(true);
        text = UtilString.replaceOnce(text, "" + mnemonic.charAt(0), "_" + mnemonic.charAt(0));
        button.setText(text);
      }
    }
  }

}
