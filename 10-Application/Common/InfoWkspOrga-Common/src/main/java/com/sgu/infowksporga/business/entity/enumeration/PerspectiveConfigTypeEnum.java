package com.sgu.infowksporga.business.entity.enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : PerspectiveConfigTypeEnum class<br>
 */
public enum PerspectiveConfigTypeEnum {
                                       /**
                                        * Or chain
                                        */
                                       XML("XML"),
                                       /**
                                        * And chain
                                        */
                                       URL("URL");

  /**
   * Store the selected value for this enum
   */
  private final String value;

  /**
   * Constructor<br>
   *
   * @param value the selected value for this enum
   */
  private PerspectiveConfigTypeEnum(final String value) {
    this.value = value;
  }

  /**
   * Description : getEnumFromString method <br>
   *
   * @param value The string value
   * @return The corresponding enum value
   */
  public static PerspectiveConfigTypeEnum getEnumFromString(final String value) {
    if (XML.getValue().equals(value)) {
      return XML;
    }
    else if (URL.getValue().equals(value)) {
      return URL;
    }
    return null;

  }

  /**
   * Description : getValuesAsList method <br>
   *
   * @return The list values of enumeration
   */
  public static List<String> getValuesAsList() {
    final List<String> values = new ArrayList<String>(values().length);
    final PerspectiveConfigTypeEnum[] enumValues = values();
    for (final PerspectiveConfigTypeEnum serachTypeEnum : enumValues) {
      values.add(serachTypeEnum.getValue());
    }

    return values;
  }

  /**
   * Gets the enum for value.
   *
   * @param value the value
   * @return the enum for value
   */
  public static PerspectiveConfigTypeEnum getEnumForValue(final String value) {
    final PerspectiveConfigTypeEnum[] enums = values();
    for (final PerspectiveConfigTypeEnum config : enums) {
      if (config.value.equals(value)) {
        return config;
      }
    }
    return null;
  }

  /**
   * value attribute getter
   *
   * @see #value
   * @return the value : See field description
   */
  public String getValue() {
    return value;
  }

}
