package com.sgu.infowksporga.jfx.binding;

import com.sgu.core.framework.gui.jfx.util.UtilStyle;
import com.sgu.core.framework.util.UtilString;

import javafx.scene.paint.Color;
import javafx.util.StringConverter;

/**
 * The Class ColorStringConverter.
 */
public class ColorStringConverter extends StringConverter<Color> {

  /**
   * The Enum ColorToStringMode.
   */
  public enum ColorToStringMode {
                                 RGB,
                                 HTML;
  }

  private ColorToStringMode convertMode;

  public ColorStringConverter() {
    this(ColorToStringMode.HTML);
  }

  public ColorStringConverter(ColorToStringMode convertMode) {
    super();
    this.convertMode = convertMode;
  }

  /** {@inheritDoc} */
  @Override
  public String toString(Color color) {
    String result = null;

    if (color == null) {
      color = Color.BLACK;
    }

    switch (convertMode) {
      case HTML:
        result = UtilStyle.getHTMLColorAsString(color);
        break;
      case RGB:
        result = UtilStyle.getRGBColorAsString(color);
        break;
      default:
        break;
    }
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public Color fromString(String strColor) {
    // init to Black by default
    Color result = Color.BLACK;

    if (UtilString.isNotBlank(strColor)) {
      switch (convertMode) {
        case HTML:
          result = UtilStyle.convertHTMLStringColorToFxColor(strColor);
          break;
        case RGB:
          result = UtilStyle.convertRGBStringColorToFxColor(strColor);
          break;
        default:
          break;
      }
    }

    return result;
  }

}