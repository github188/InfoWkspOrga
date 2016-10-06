package com.sgu.infowksporga.business.xml.jaxb.cmmi;

import java.io.Serializable;

/**
 * Description : CategoryEnum class<br>
 *
 */
public enum CategoryEnum implements Serializable {
  /**
   * PROCESS_MANAGMENT category
   */
  PROCESS_MANAGMENT("Process Management"),

  /**
   * PROJECT_MANAGMENT category
   */
  PROJECT_MANAGMENT("Project Management");

  /**
   * Store the value of the enum
   */
  private String value;

  /**
   * Constructor<br>
   *
   * @param value the enum value
   */
  private CategoryEnum(final String value) {
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
   * 
   * Description : Retrieve the Enum corresponding to the value <br>
   *
   * @author SGU
   *
   * @param value of enum
   * @return Enum The Enum
   *
   */
  public static CategoryEnum getEnumForValue(final String value) {
    final CategoryEnum[] enums = values();
    for (final CategoryEnum right : enums) {
      if (right.value.equals(value)) {
        return right;
      }
    }
    return null;
  }

}
