package com.sgu.infowksporga.business.entity;

/**
 * The Interface IStylable.
 */
public interface IStylable {

  /**
   * Gets the bg color.
   *
   * @return the bg color
   */
  String getBgColor();

  /**
   * Gets the color.
   *
   * @return the color
   */
  String getColor();

  /**
   * Gets the icon.
   *
   * @return the icon
   */
  String getIcon();

  /**
   * Checks if is bold.
   *
   * @return true, if checks if is bold
   */
  boolean isBold();

  /**
   * Checks if is italic.
   *
   * @return true, if checks if is italic
   */
  boolean isItalic();

  /**
   * Checks if is strike.
   *
   * @return true, if checks if is strike
   */
  boolean isStrike();

  /**
   * Checks if is underline.
   *
   * @return true, if checks if is underline
   */
  boolean isUnderline();

}
