package com.sgu.infowksporga.business.entity.enumeration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.i18n.I18nHelperFwk;
import com.sgu.core.framework.i18n.util.I18NConstant;

/**
 * Description : Partage Enum class<br>
 *
 * @author SGU
 */

@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-common",
properties = { // Force /n
              @I18nProperty(key = "enum.partage.private" + I18NConstant.TEXT, value = "Personnel"), // Force /n
              @I18nProperty(key = "enum.partage.private" + I18NConstant.DESCRIPTION, value = "Workspace uniquement visible par le propriétaire"), // Force /n
              @I18nProperty(key = "enum.partage.private" + I18NConstant.ICON, value = "/icons/user.png"), // Force /n
              //-----------------
              @I18nProperty(key = "enum.partage.public" + I18NConstant.TEXT, value = "Public"), // Force /n
              @I18nProperty(key = "enum.partage.public" + I18NConstant.DESCRIPTION, value = "Workspace visible par tout le monde"), // Force /n
              @I18nProperty(key = "enum.partage.public" + I18NConstant.ICON, value = "/icons/users.png"), // Force /n
              //-----------------
              @I18nProperty(key = "enum.partage.authorization" + I18NConstant.TEXT, value = "Soumis à authorisation"), // Force /n
              @I18nProperty(key = "enum.partage.authorization" + I18NConstant.DESCRIPTION,
              value = "Seulement les utilisateurs ayant des droits sur ce Workspace pourront y accéder"), // Force /n
              @I18nProperty(key = "enum.partage.authorization" + I18NConstant.ICON, value = "/icons/lock.png"), // Force /n
})
public enum PartageEnum implements Serializable {
                                                 PRIVATE("PRIVATE", "enum.partage.private"),
                                                 PUBLIC("PUBLIC", "enum.partage.public"),
                                                 AUTHORIZATION("AUTHORIZATION", "enum.partage.authorization");

  /**
   * The String value of the Enum
   */
  private String value;

  /** The i18n key. */
  private String bundleKey;

  /**
   * Constructor<br>
   *
   * @param value
   */
  PartageEnum(final String value, final String bundleKey) {
    this.value = value;
    this.bundleKey = bundleKey;
  }

  /**
   * Description : getEnumFromString method <br>
   *
   * @param value The value to check
   * @return The enum corresponding to the given String
   */
  public static PartageEnum getEnumForValue(final String value) {
    if (value != null) {
      for (final PartageEnum b : PartageEnum.values()) {
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
    final PartageEnum[] enumValues = values();
    for (final PartageEnum partageEnum : enumValues) {
      values.add(partageEnum.getValue());
    }

    return values;
  }

  /**
   * Gets the enum as ordered list.
   *
   * @return the enum as ordered list
   */
  public static List<PartageEnum> getEnumAsOrderedList() {
    final List<PartageEnum> list = new ArrayList<PartageEnum>(values().length);
    final PartageEnum[] enumValues = values();
    for (final PartageEnum partageEnum : enumValues) {
      list.add(partageEnum);
    }

    return list;
  }

  /**
   * @see #value
   * @return the value : See field description
   */
  public String getValue() {
    return value;
  }

  /**
   * @return the bundleKey
   */
  public final String getBundleKey() {
    return bundleKey;
  }

  /**
   * Gets the i18n text.
   *
   * @return the i18n text
   */
  public String getI18nText() {
    return I18nHelperFwk.getGivenDefaultMessageIfNull(bundleKey + I18NConstant.TEXT, "???");
  }

  /**
   * Gets the i18n icon.
   *
   * @return the i18n icon
   */
  public String getI18nIcon() {
    return I18nHelperFwk.getNullMessage(bundleKey + I18NConstant.ICON);
  }

  /**
   * Gets the i18n description.
   *
   * @return the i18n description
   */
  public String getI18nDescription() {
    return I18nHelperFwk.getNullMessage(bundleKey + I18NConstant.DESCRIPTION);
  }

}
