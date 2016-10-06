package com.sgu.infowksporga.business.xml.jaxb.cmmi;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : CmmiModelEnum class<br>
 */
public enum CmmiModelTypeEnum {
                               /**
                                * The Development Model
                                */
                               DEVELOPMENT("DEV"),

                               /**
                                * The Acquisition Model
                                */
                               ACQUISITION("ACQ"),

                               /**
                                * The Service Model
                                */
                               SERVICE("SRV");

  /**
   * The String value of the Enum
   */
  private String value;

  /**
   * Constructor<br>
   *
   * @param value
   */
  CmmiModelTypeEnum(final String value) {
    this.value = value;
  }

  /**
   * Description : getEnumFromString method <br>
   *
   * @param value The value to check
   * @return The enum corresponding to the given String
   */
  public static CmmiModelTypeEnum getEnumFromString(final String value) {
    if (value != null) {
      for (final CmmiModelTypeEnum b : CmmiModelTypeEnum.values()) {
        if (value.equalsIgnoreCase(b.value)) {
          return b;
        }
      }
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
    final CmmiModelTypeEnum[] enumValues = values();
    for (final CmmiModelTypeEnum cmmiModelEnum : enumValues) {
      values.add(cmmiModelEnum.getValue());
    }

    return values;
  }

  /**
   * @see #value
   * @return the value : See field description
   */
  public String getValue() {
    return value;
  }

}
