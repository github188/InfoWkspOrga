package com.sgu.infowksporga.business.entity.enumeration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Enum AttributeValueLocalizationEnum.
 */
public enum AttributeValueLocalizationEnum implements Serializable {

                                                                    /** Database. */
                                                                    DATABASE("DATABASE"),

                                                                    /** LOCAL PROPERTIES FILE. */
                                                                    LOCAL_PROPERTIES_FILE("LOCAL_PROPERTIES_FILE");

  /** Store the value of the enum. */
  private String value;

  /**
   * Constructor<br>.
   *
   * @param value the enum value
   */
  private AttributeValueLocalizationEnum(final String value) {
    this.value = value;
  }

  /**
   * Gets the value.
   *
   * @return the value : See field description
   * @see #value
   */
  public String getValue() {
    return value;
  }

  /**
   * Description : Retrieve the Enum corresponding to the value <br>.
   *
   * @author SGU
   * @param value of enum
   * @return Enum The Enum
   */
  public static AttributeValueLocalizationEnum getEnumForValue(final String value) {
    final AttributeValueLocalizationEnum[] enums = values();
    for (final AttributeValueLocalizationEnum partage : enums) {
      if (partage.value.equals(value)) {
        return partage;
      }
    }
    return null;
  }

  /**
   * Description : getValuesAsList method <br>.
   *
   * @return The list values of enumeration
   */
  public static List<String> getValuesAsList() {
    final List<String> values = new ArrayList<String>(values().length);
    final AttributeValueLocalizationEnum[] enumValues = values();
    for (final AttributeValueLocalizationEnum partageEnum : enumValues) {
      values.add(partageEnum.getValue());
    }

    return values;
  }

}
