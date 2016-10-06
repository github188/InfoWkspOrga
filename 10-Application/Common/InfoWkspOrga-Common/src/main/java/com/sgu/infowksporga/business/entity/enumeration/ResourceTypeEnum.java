package com.sgu.infowksporga.business.entity.enumeration;

/**
 * Description : ResourceType Enum br>
 *
 * @author SGU
 */
public enum ResourceTypeEnum {
                              /**
                               * Project resource type
                               */
                              PROJECT("Project");

  /**
   * Store the value of the enum
   */
  private String value;

  /**
   * Constructor<br>
   *
   * @param value the enum value
   */
  private ResourceTypeEnum(final String value) {
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
  public static ResourceTypeEnum getEnumForValue(final String value) {
    final ResourceTypeEnum[] enums = values();
    for (final ResourceTypeEnum resourceType : enums) {
      if (resourceType.value.equals(value)) {
        return resourceType;
      }
    }
    return null;
  }
}
