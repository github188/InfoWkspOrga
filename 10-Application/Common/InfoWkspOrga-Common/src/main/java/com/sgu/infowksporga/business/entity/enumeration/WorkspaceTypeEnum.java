package com.sgu.infowksporga.business.entity.enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : WorkspaceTypeEnum class<br>
 */
public enum WorkspaceTypeEnum {
                               ROOT("ROOT"),
                               CLASSEUR("CLASSEUR"),
                               HELP("HELP"),
                               USER("USER"),
                               ENTREPRISE("ENTREPRISE"),
                               PROJECT("PROJECT"),
                               TMA("TMA"),
                               TEMPLATE("TEMPLATE"),
                               GESTION("GESTION"),
                               TOOLS("TOOLS"),
                               DOCUMENTATION("DOCUMENTATION");

  /**
   * The String value of the Enum
   */
  private String value;

  /**
   * Constructor<br>
   *
   * @param value
   */
  WorkspaceTypeEnum(final String value) {
    this.value = value;
  }

  /**
   * Description : getEnumFromString method <br>
   *
   * @param value The value to check
   * @return The enum corresponding to the given String
   */
  public static WorkspaceTypeEnum getEnumForValue(final String value) {
    if (value != null) {
      for (final WorkspaceTypeEnum b : WorkspaceTypeEnum.values()) {
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
    final WorkspaceTypeEnum[] enumValues = values();
    for (final WorkspaceTypeEnum workspaceTypeEnum : enumValues) {
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
