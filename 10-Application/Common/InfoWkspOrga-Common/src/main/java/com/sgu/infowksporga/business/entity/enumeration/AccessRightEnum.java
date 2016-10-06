package com.sgu.infowksporga.business.entity.enumeration;

import java.io.Serializable;

/**
 * Description : Access Right Enum class<br>
 *
 * @author SGU
 */
public enum AccessRightEnum implements Serializable {

                                                     /**
                                                      * READ WRITE Access type
                                                      */
                                                     READ_WRITE("W"),

                                                     /**
                                                      * READ Access type
                                                      */
                                                     READ("R"),

                                                     /**
                                                      * Invisible resource for the user
                                                      */
                                                     INVISIBLE("I");

  /**
   * Store the value of the enum
   */
  private String value;

  /**
   * Constructor<br>
   *
   * @param value the enum value
   */
  private AccessRightEnum(final String value) {
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
  public static AccessRightEnum getEnumForValue(final String value) {
    final AccessRightEnum[] enums = values();
    for (final AccessRightEnum right : enums) {
      if (right.value.equals(value)) {
        return right;
      }
    }
    return null;
  }

}
