package com.sgu.infowksporga.business.entity.enumeration;

import java.io.Serializable;

/**
 * The Enum PreferenceLevelEnum.
 */
public enum PreferenceLevelEnum implements Serializable {

                                                         /**
                                                          * USER preference
                                                          */
                                                         USER("USER"),

                                                         /**
                                                          * GLOBAL preference
                                                          */
                                                         GLOBAL("GLOBAL");

  /**
   * Store the value of the enum
   */
  private String value;

  /**
   * Constructor<br>
   *
   * @param value the enum value
   */
  private PreferenceLevelEnum(final String value) {
    this.value = value;
  }

  /**
   * @see #value
   * @return the value : See field description
   */
  public String getValue() {
    return value;
  }

  /**
   * Description : Retrieve the Enum corresponding to the value <br>
   *
   * @author SGU
   * @param value of enum
   * @return Enum The Enum
   */
  public static PreferenceLevelEnum getEnumForValue(final String value) {
    final PreferenceLevelEnum[] enums = values();
    for (final PreferenceLevelEnum right : enums) {
      if (right.value.equals(value)) {
        return right;
      }
    }
    return null;
  }

}
