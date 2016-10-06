package com.sgu.infowksporga.business.entity.enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : WorkspaceTypeEnum class<br>
 */
public enum WorkspaceSubTypeEnum {
                                  /**
                                   * The standard workspace type
                                   */
                                  STANDARD("STANDARD"),
                                  /**
                                   * The COMMON type means : workspace layout is identical for all users but project can modify their proper
                                   * view configuration
                                   */
                                  COMMON("COMMON");

  /**
   * The String value of the Enum
   */
  private String value;

  /**
   * Constructor<br>
   *
   * @param value
   */
  WorkspaceSubTypeEnum(final String value) {
    this.value = value;
  }

  /**
   * Description : getEnumFromString method <br>
   *
   * @param value The value to check
   * @return The enum corresponding to the given String
   */
  public static WorkspaceSubTypeEnum getEnumForValue(final String value) {
    if (value != null) {
      for (final WorkspaceSubTypeEnum b : WorkspaceSubTypeEnum.values()) {
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
    final WorkspaceSubTypeEnum[] enumValues = values();
    for (final WorkspaceSubTypeEnum workspaceTypeEnum : enumValues) {
      values.add(workspaceTypeEnum.getValue());
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
