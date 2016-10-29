package com.sgu.infowksporga.jfx.util;

import java.util.Date;

import com.sgu.core.framework.util.UtilDate;

import javafx.beans.property.ObjectProperty;

/**
 * The Class UtilAppUI.
 */
public final class UtilAppUI {

  /**
   * The Constructor.
   */
  private UtilAppUI() {
  }

  /**
   * Format date.
   *
   * @param dateProperty the date property
   * @return the string
   */
  public static String formatDate(final ObjectProperty<Date> dateProperty) {
    if (dateProperty != null && dateProperty.getValue() != null) {
      return GUISessionProxy.getApplicationDateTimeFormatter().format(UtilDate.convertUtilDateAsLocalDateTime(dateProperty.getValue()));
    }

    return "";

  }
}
