package com.sgu.infowksporga.business.entity.enumeration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description : DockPos Enum class<br>
 *
 * @author SGU
 */
public enum DockPosEnum implements Serializable {

                                                 /**
                                                  * RIGHT Position
                                                  */
                                                 RIGHT("RIGHT"),

                                                 /**
                                                  * BOTTOM Position to build TabPane
                                                  */
                                                 CENTER("CENTER"),

                                                 /**
                                                  * BOTTOM Position
                                                  */
                                                 BOTTOM("BOTTOM");

  /**
   * Store the value of the enum
   */
  private String value;

  /**
   * Constructor<br>
   *
   * @param value the enum value
   */
  private DockPosEnum(final String value) {
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
  public static DockPosEnum getEnumForValue(final String value) {
    final DockPosEnum[] enums = values();
    for (final DockPosEnum partage : enums) {
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
    final DockPosEnum[] enumValues = values();
    for (final DockPosEnum partageEnum : enumValues) {
      values.add(partageEnum.getValue());
    }

    return values;
  }

}
