package com.sgu.infowksporga.business.entity.enumeration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description : Partage Enum class<br>
 *
 * @author SGU
 */
public enum PartageEnum implements Serializable {

                                                 /**
                                                  * Private Access
                                                  */
                                                 PRIVATE("PRIVATE"),

                                                 /**
                                                  * Public Access
                                                  */
                                                 PUBLIC("PUBLIC"),

                                                 /**
                                                  * Invisible resource for the user
                                                  */
                                                 AUTHORIZATION("AUTHORIZATION");

  /**
   * Store the value of the enum
   */
  private String value;

  /**
   * Constructor<br>
   *
   * @param value the enum value
   */
  private PartageEnum(final String value) {
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
  public static PartageEnum getEnumForValue(final String value) {
    final PartageEnum[] enums = values();
    for (final PartageEnum partage : enums) {
      if (partage.value.equals(value)) {
        return partage;
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
    final PartageEnum[] enumValues = values();
    for (final PartageEnum partageEnum : enumValues) {
      values.add(partageEnum.getValue());
    }

    return values;
  }

}
