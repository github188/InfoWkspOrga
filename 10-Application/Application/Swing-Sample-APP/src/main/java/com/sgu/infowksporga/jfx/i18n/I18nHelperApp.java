package com.sgu.infowksporga.jfx.i18n;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;

import com.sgu.core.framework.i18n.I18nHelper;
import com.sgu.core.framework.i18n.LocalI18nService;
import com.sgu.core.framework.spring.ReloadableResourceBundleMessageSource;

/**
 * The Class I18nHelperApp.
 */
public class I18nHelperApp {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(I18nHelperApp.class);

  /** The i18n helper. */
  protected static I18nHelper i18nHelper;

  /**
   * The Constructor.
   * This I18nHelperAppSwing should be initialized by the Application
   */
  protected I18nHelperApp() {
  }

  /**
   * Inits the.
   */
  public static void init() {
    i18nHelper = new I18nHelper();

    final LocalI18nService i18nService = new LocalI18nService();
    i18nService.getDefinedLocale().add("fr");
    i18nService.getDefinedLocale().add("en");

    final ReloadableResourceBundleMessageSource rrbms = new ReloadableResourceBundleMessageSource();
    rrbms.setResourceLoader(new DefaultResourceLoader(I18nHelperApp.class.getClassLoader()));
    rrbms.setBasenames("i18n/infowrksporga_message", "i18n/gfi_message");
    i18nService.setResourceBundleMessageSource(rrbms);

    i18nHelper.setI18nService(i18nService);
  }

  /**
   * Inits the.
   *
   * @param I18nHelperApp the i18n helper
   */
  public static void init(final I18nHelper I18nHelper) {
    I18nHelperApp.i18nHelper = I18nHelper;
  }

  /**
   * Sets the locale.
   *
   * @param locale the locale
   */

  public static void setLocale(final Locale locale) {
    getI18nHelper().setLocale(locale);

  }

  /**
   * Gets the message.
   *
   * @param key the key
   * @param arguments the arguments
   * @return the message
   */

  public static String getMessage(final String key, final Object... arguments) {
    return getI18nHelper().getMessage(key, arguments);
  }

  /**
   * Gets the message.
   *
   * @param key the key
   * @return the message
   */

  public static String getMessage(final String key) {
    return getI18nHelper().getMessage(key);
  }

  /**
   * Gets the given default message if null.
   *
   * @param key the key
   * @param defaultMessage the default message
   * @return the given default message if null
   */

  public static String getGivenDefaultMessageIfNull(final String key, final String defaultMessage) {
    return getI18nHelper().getGivenDefaultMessageIfNull(key, defaultMessage);
  }

  /**
   * Gets the given default message if null.
   *
   * @param key the key
   * @param defaultMessage the default message
   * @param args the args
   * @return the given default message if null
   */

  public static String getGivenDefaultMessageIfNull(final String key, final String defaultMessage, final Object... args) {
    return getI18nHelper().getGivenDefaultMessageIfNull(key, defaultMessage, args);
  }

  /**
   * Gets the null message.
   *
   * @param key the key
   * @return the null message
   */

  public static String getNullMessage(final String key) {
    return getI18nHelper().getNullMessage(key);
  }

  /**
   * Gets the null message.
   *
   * @param key the key
   * @param args the args
   * @return the null message
   */
  public static String getNullMessage(final String key, final Object... args) {
    return getI18nHelper().getNullMessage(key, args);
  }

  /**
   * Gets the i18n service.
   *
   * @return the i18n service
   */

  public static LocalI18nService getI18nService() {
    return getI18nHelper().getI18nService();
  }

  /**
   * Gets the user locale.
   *
   * @return the user locale
   */

  public static Locale getUserLocale() {
    return getI18nHelper().getUserLocale();
  }

  /**
   * Gets the i18n helper.
   *
   * @return the i18n helper
   */
  public static I18nHelper getI18nHelper() {
    if (i18nHelper == null) {
      init();
    }
    return i18nHelper;
  }

}
