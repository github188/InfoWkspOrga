package com.sgu.infowksporga.business.entity.enumeration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.i18n.I18nHelperFwk;
import com.sgu.core.framework.i18n.util.I18NConstant;

/**
 * Description : CategoryEnum class<br>
 */
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-common",
properties = { // Force /n
              @I18nProperty(key = "workspace.category.root" + I18NConstant.TEXT, value = "Root"), // Force /n
              @I18nProperty(key = "workspace.category.root" + I18NConstant.ICON, value = "/icons/root.png"), // Force /n
              //---------------
              @I18nProperty(key = "workspace.category.classeur" + I18NConstant.TEXT, value = "Classeur"), // Force /n
              @I18nProperty(key = "workspace.category.classeur" + I18NConstant.ICON, value = "/icons/Address-book.png"), // Force /n
              //---------------
              @I18nProperty(key = "workspace.category.help" + I18NConstant.TEXT, value = "Aide"), // Force /n
              @I18nProperty(key = "workspace.category.help" + I18NConstant.ICON, value = "/icons/help.png"), // Force /n
              //---------------
              @I18nProperty(key = "workspace.category.user" + I18NConstant.TEXT, value = "Utilisateur"), // Force /n
              @I18nProperty(key = "workspace.category.user" + I18NConstant.ICON, value = "/icons/user.png"), // Force /n
              //---------------
              @I18nProperty(key = "workspace.category.entreprise" + I18NConstant.TEXT, value = "Entreprise"), // Force /n
              @I18nProperty(key = "workspace.category.entreprise" + I18NConstant.ICON, value = "/icons/enterprise.png"), // Force /n
              //---------------
              @I18nProperty(key = "workspace.category.project" + I18NConstant.TEXT, value = "Projet"), // Force /n
              @I18nProperty(key = "workspace.category.project" + I18NConstant.ICON, value = "/icons/project.png"), // Force /n
              //---------------
              @I18nProperty(key = "workspace.category.tma" + I18NConstant.TEXT, value = "TMA"), // Force /n
              @I18nProperty(key = "workspace.category.tma" + I18NConstant.ICON, value = "/icons/tma.png"), // Force /n
              //---------------
              @I18nProperty(key = "workspace.category.template" + I18NConstant.TEXT, value = "Modéle"), // Force /n
              @I18nProperty(key = "workspace.category.template" + I18NConstant.TOOLTIP_TEXT,
              value = "Ce type de wokspace peut servir de base pour d'autres (cf champ Master)"), // Force /n
              @I18nProperty(key = "workspace.category.template" + I18NConstant.ICON, value = "/icons/template.png"), // Force /n
              //---------------
              @I18nProperty(key = "workspace.category.gestion" + I18NConstant.TEXT, value = "Gestion"), // Force /n
              @I18nProperty(key = "workspace.category.gestion" + I18NConstant.ICON, value = "/icons/toolbox.png"), // Force /n
              //---------------
              @I18nProperty(key = "workspace.category.tools" + I18NConstant.TEXT, value = "Outils"), // Force /n
              @I18nProperty(key = "workspace.category.tools" + I18NConstant.ICON, value = "/icons/toolbox.png"), // Force /n
              //---------------
              @I18nProperty(key = "workspace.category.documentation" + I18NConstant.TEXT, value = "Documentation"), // Force /n
              @I18nProperty(key = "workspace.category.documentation" + I18NConstant.ICON, value = "/icons/books.png"), // Force /n
              //---------------
              @I18nProperty(key = "workspace.category.other" + I18NConstant.TEXT, value = "Autre"), // Force /n
              @I18nProperty(key = "workspace.category.other" + I18NConstant.ICON, value = "/icons/information.png"), // Force /n
})
public enum CategoryEnum {
                          ROOT("ROOT", "workspace.category.root"),
                          CLASSEUR("CLASSEUR", "workspace.category.classeur"),
                          HELP("HELP", "workspace.category.help"),
                          USER("USER", "workspace.category.user"),
                          ENTREPRISE("ENTREPRISE", "workspace.category.entreprise"),
                          PROJECT("PROJECT", "workspace.category.project"),
                          TMA("TMA", "workspace.category.tma"),
                          TEMPLATE("TEMPLATE", "workspace.category.template"),
                          GESTION("GESTION", "workspace.category.gestion"),
                          TOOLS("TOOLS", "workspace.category.tools"),
                          DOCUMENTATION("DOCUMENTATION", "workspace.category.documentation"),
                          OTHER("OTHER", "workspace.category.other");

  /** The Constant comparator. */
  private static final CategoryEnumOldComparator comparator = new CategoryEnumOldComparator();

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
  CategoryEnum(final String value, final String bundleKey) {
    this.value = value;
    this.bundleKey = bundleKey;
  }

  /**
   * Description : getEnumFromString method <br>
   *
   * @param value The value to check
   * @return The enum corresponding to the given String
   */
  public static CategoryEnum getEnumForValue(final String value) {
    if (value != null) {
      for (final CategoryEnum b : CategoryEnum.values()) {
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
    final CategoryEnum[] enumValues = values();
    for (final CategoryEnum workspaceTypeEnum : enumValues) {
      values.add(workspaceTypeEnum.getValue());
    }

    return values;
  }

  /**
   * Gets the enum as ordered list.
   *
   * @return the enum as ordered list
   */
  public static List<CategoryEnum> getEnumAsOrderedList() {
    final List<CategoryEnum> list = new ArrayList<CategoryEnum>(values().length);
    final CategoryEnum[] enumValues = values();
    for (final CategoryEnum workspaceTypeEnum : enumValues) {
      list.add(workspaceTypeEnum);
    }

    Collections.sort(list, comparator);

    return list;
  }

  /**
   * The Class WorkspaceTypeEnumOldComparator.
   */
  public static class CategoryEnumOldComparator implements Comparator<CategoryEnum> {

    /** {@inheritDoc} */
    @Override
    public int compare(final CategoryEnum o1, final CategoryEnum o2) {

      final String str1 = o1.getI18nText();
      final String str2 = o2.getI18nText();

      return str1.compareToIgnoreCase(str2);
    }

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
