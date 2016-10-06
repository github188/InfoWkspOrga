package com.sgu.infowksporga.jfx.connexion.cbbox;

import java.util.Locale;

/**
 * Description : ComboBoxLanguageVo class<br>
 */
public class ComboBoxLanguageVo {
  /**
   * Language label it can be a I18N code
   */
  private String label;

  /**
   * The icon flag associated with the language
   */
  private String flag;

  /**
   * the Locale
   */
  private Locale locale;

  /**
   * Constructor<br>
   * 
   * @param label language label (I18N value)
   * @param flag the icon represent the country flag
   * @param locale the Locale associated with the displayed language
   */
  public ComboBoxLanguageVo(final String label, final String flag, final Locale locale) {
    this.label = label;
    this.flag = flag;
    this.locale = locale;
  }

  /**
   * @see #label
   * @return the label : See field description
   */
  public String getLabel() {
    return label;
  }

  /**
   * @see #label
   * @param label : See field description
   */
  public void setLabel(final String label) {
    this.label = label;
  }

  /**
   * @see #flag
   * @return the flag : See field description
   */
  public String getFlag() {
    return flag;
  }

  /**
   * @see #flag
   * @param flag : See field description
   */
  public void setFlag(final String flag) {
    this.flag = flag;
  }

  /**
   * @see #locale
   * @return the locale : See field description
   */
  public Locale getLocale() {
    return locale;
  }

  /**
   * @see #locale
   * @param locale : See field description
   */
  public void setLocale(final Locale locale) {
    this.locale = locale;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (label == null ? 0 : label.hashCode());
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final ComboBoxLanguageVo other = (ComboBoxLanguageVo) obj;
    if (label == null) {
      if (other.label != null) {
        return false;
      }
    }
    else if (!label.equals(other.label)) {
      return false;
    }
    return true;
  }

}
