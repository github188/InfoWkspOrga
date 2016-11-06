package com.sgu.infowksporga.jfx.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.i18n.I18nHelperJavaFX;
import com.sgu.core.framework.gui.jfx.util.GUISession;
import com.sgu.core.framework.gui.jfx.util.UserPreferencesConstant;
import com.sgu.core.framework.gui.jfx.util.UtilApplication;
import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.core.framework.i18n.I18nHelper;
import com.sgu.core.framework.i18n.I18nHelperFwk;
import com.sgu.core.framework.i18n.LocalI18nService;
import com.sgu.core.framework.io.DirectoryFile;
import com.sgu.core.framework.pivot.UserInfo;
import com.sgu.core.framework.spring.ReloadableResourceBundleMessageSource;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.core.framework.spring.service.remote.IGetRemoteSpringObjectService;
import com.sgu.core.framework.util.Properties;
import com.sgu.core.framework.util.UtilDate;
import com.sgu.core.framework.util.UtilIO;
import com.sgu.core.framework.util.UtilSpring;
import com.sgu.infowksporga.business.xml.jaxb.menu.MenuApplication;
import com.sgu.infowksporga.business.xml.jaxb.toolbar.ToolbarApplication;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class ApplicationInitializer.
 */
@Slf4j
public final class ApplicationInitializer {

  /**
   * The Constructor.
   */
  private ApplicationInitializer() {
    // Utility Class
  }

  /**
   * Authenticate user.
   */
  public static void authenticateUser() {
    final UserInfo userInfo = new UserInfo("sguisse", "Guisse", "Sébastien", "sebastien.guisse@gfi.fr", "fr_FR", "fr_FR");
    GUISessionProxy.getGuiSession().setCurrentUser(userInfo);
  }

  /**
   * Description : initialize Internationalization Message Bundle method <br>
   */
  @SuppressWarnings("unchecked")
  public static void initializeI18nMessageBundle() {
    // Get I18N files content from presentation server (Shared lib)

    final I18nHelper i18nHelper = new I18nHelper();

    final LocalI18nService i18nService = new LocalI18nService();
    i18nService.getDefinedLocale().add("fr");
    i18nService.getDefinedLocale().add("en");

    final ReloadableResourceBundleMessageSource rrbms = new ReloadableResourceBundleMessageSource();
    rrbms.setResourceLoader(new DefaultResourceLoader(I18nHelperFwk.class.getClassLoader()));
    rrbms.setBasenames("i18n/fwk-core", "i18n/fwk-core-field-format", "i18n/fwk-core-validation", "i18n/javafx_framework_message", "i18n/infowksporga_message",
                       "i18n/application-prez", "i18n/application-biz", "i18n/application-common", "spring/server-localization");
    i18nService.setResourceBundleMessageSource(rrbms);

    i18nHelper.setI18nService(i18nService);

    // We the same helper for all projects
    I18nHelperFwk.init(i18nHelper);
    I18nHelperJavaFX.init(i18nHelper);
    I18nHelperApp.init(i18nHelper);
  }

  /**
   * Description : initializeApplicationToolbar method <br>
   */
  public static void initializeApplicationToolbar() {

    // Convert XML configuration with JAXB
    try {
      final ClassLoader cl = com.sgu.infowksporga.business.xml.jaxb.toolbar.ObjectFactory.class.getClassLoader();
      final JAXBContext jc = JAXBContext.newInstance("com.sgu.infowksporga.business.xml.jaxb.toolbar", cl);
      final Unmarshaller unmarshaller = jc.createUnmarshaller();
      final InputStream is = UtilIO.getClasspathFileInputStream("toolbar-application.xml");
      final ToolbarApplication configuration = (ToolbarApplication) unmarshaller.unmarshal(is);
      //ActionManager.toolbarApplication = configuration;
    } catch (final JAXBException e) {
      log.error(e.getMessage(), e);
      throw new TechnicalException("infowrksporga.error.menu.configuration.bad.xml", e);
    }

  }

  /**
   * Description : initializeApplicationMenu method <br>
   */
  public static void initializeApplicationMenu() {

    // Convert XML configuration with JAXB
    try {
      final ClassLoader cl = com.sgu.infowksporga.business.xml.jaxb.menu.ObjectFactory.class.getClassLoader();
      final JAXBContext jc = JAXBContext.newInstance("com.sgu.infowksporga.business.xml.jaxb.menu", cl);
      final Unmarshaller unmarshaller = jc.createUnmarshaller();
      final InputStream is = UtilIO.getClasspathFileInputStream("menu-application.xml");
      final MenuApplication configuration = (MenuApplication) unmarshaller.unmarshal(is);
      //ActionManager.menuApplication = configuration;
    } catch (final JAXBException e) {
      log.error(e.getMessage(), e);
      throw new TechnicalException("infowrksporga.error.toolbar.configuration.bad.xml", e);
    }

  }

  /**
   * Description : initializeCarrouselImages method <br>
   * Store all the carrousel images in session
   */
  public static void initializeCarrouselImages() {
    final IGetRemoteSpringObjectService prezService = SpringBeanHelper.getImplementationByInterface(IGetRemoteSpringObjectService.class);
    final List<ImageIcon> carrouselImages = prezService.getCarrouselImages();
    GUISessionProxy.setCarrouselImages(carrouselImages);
  }

  /**
   * Description : initializeCarrouselImages method <br>
   * Store all the carrousel images in session
   */
  public static void initializeSpringConfiguration() {
    UtilSpring.initializeSpring("/spring/application-context.xml");
  }

  /**
   * Description : verifyAllRemoteConnexionAreAvailable method <br>
   */

  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "message.error.control.prez.server.title", value = "Contrôle du serveur de Présentation"), // Force /n
                @I18nProperty(key = "message.error.control.prez.server.body",
                value = "Le serveur de 'Présentation' est inaccessible\n{0}\nVeuillez contacter l'administrateur \n"
                        + "en joignant la copie du texte contenue dans le bloc détail\n" + "Merci."), // Force /n
                @I18nProperty(key = "message.error.control.biz.server.title", value = "Contrôle du serveur Métier"), // Force /n
                @I18nProperty(key = "message.error.control.biz.server.body",
                value = "Le serveur de 'Métier' est inaccessible\n{0}\nVeuillez contacter l'administrateur \n"
                        + "en joignant la copie du texte contenue dans le bloc détail\n" + "Merci."), // Force /n
                @I18nProperty(key = "message.error.control.bdd.server.title", value = "Contrôle du serveur de Base de Données"), // Force /n
                @I18nProperty(key = "message.error.control.bdd.server.body",
                value = "Le serveur de 'Base de Données' est inaccessible\n{0}\nVeuillez contacter l'administrateur \n"
                        + "en joignant la copie du texte contenue dans le bloc détail\n" + "Merci."), // Force /n
  })
  public static void verifyAllRemoteConnexionAreAvailable() {
    // Verify Prez server is accessible
    final String prezStrUrl = SpringBeanHelper.getImplementationByName("prez.available.url.test", String.class);
    try {
      final URL prezURL = new URL(prezStrUrl);
      final HttpURLConnection prezURLConnection = (HttpURLConnection) prezURL.openConnection();
      prezURLConnection.setRequestMethod("HEAD");
      prezURLConnection.connect();
      if (prezURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
        throw new TechnicalException("Response code = " + prezURLConnection.getResponseCode());
      }
    } catch (final Exception e) {
      log.error(e.getMessage(), e);
      UtilGUIMessage.showErrorWithDetails("message.error.control.prez.server.title", I18nHelperApp.getMessage("message.error.control.prez.server.body", prezStrUrl), e);
      System.exit(0);
    }

    // Verify Business server is accessible
    final String bizStrUrl = SpringBeanHelper.getImplementationByName("biz.available.url.test", String.class);
    try {
      final URL bizURL = new URL(bizStrUrl);
      final HttpURLConnection bizURLConnection = (HttpURLConnection) bizURL.openConnection();
      bizURLConnection.setRequestMethod("HEAD");
      bizURLConnection.connect();
      if (bizURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
        throw new TechnicalException("Response code = " + bizURLConnection.getResponseCode());
      }
    } catch (final Exception e) {
      log.error(e.getMessage(), e);
      UtilGUIMessage.showErrorWithDetails("message.error.control.biz.server.title", I18nHelperApp.getMessage("message.error.control.biz.server.body", bizStrUrl), e);
      System.exit(0);
    }

    // Verify Database is accessible
    try {
      //databaseAccessibleVerifierBo.checkDatabaseIsAvailable();
    } catch (final TechnicalException e1) {
      log.warn(e1.getMessage(), e1);

      try { // try a second time
        UtilDate.delay(5000);
        //databaseAccessibleVerifierBo.checkDatabaseIsAvailable();
      } catch (final TechnicalException e2) {
        log.error(e2.getMessage(), e2);
        UtilGUIMessage.showErrorWithDetails("message.error.control.bdd.server.title", I18nHelperApp.getMessage("message.error.control.bdd.server.body"), e2);
        System.exit(0);
      }

    }
  }

  /**
   * Description : manage Application Exit (click on close button of the frame) <br>
   */
  public static void initializeApplicationExitManagement() {

  }

  /**
   * Description : initialize Application Language method <br>
   */
  public static void initializeApplicationLanguage() {
    Locale locale = null;

    String strLocale = null;

    // Look in user settings file if exists
    if (locale == null) {
      strLocale = GUISession.getInstance().getUserLocalSettings().getProperty(UserPreferencesConstant.USER_LANGUAGE_SETTING);
      if (strLocale != null) {
        locale = new Locale(strLocale);
      }
    }

    if (locale == null) {
      locale = Locale.getDefault();
    }

    I18nHelperApp.getI18nHelper().setLocale(locale);
  }

  /**
   * Description : initialize User Local Settings Properties method <br>
   */
  public static void initializeUserLocalSettingsProperties() {

    final Properties userLocalSettings = new Properties();
    final String userConfigFileLocation = UtilApplication.getUserLocalSettingsPropertiesPath();
    try {
      userLocalSettings.load(new FileInputStream(userConfigFileLocation));
    } catch (final FileNotFoundException e) {
      throw new TechnicalException(e);
    } catch (final IOException e) {
      throw new TechnicalException(e);
    }

    GUISession.getInstance().setUserLocalSettings(userLocalSettings);

  }

  /**
   * Description : initialize User Application Home method <br>
   */
  public static void initializeUserApplicationHome() {

    final String appVersion = getApplicationVersion();

    final String userApplicationHome = System.getProperty("user.home") + "/Application Data/" + getApplicationNameShort() + "/" + appVersion;

    log.debug("La localisation du fichier de preferences utilisateur : '{}'", userApplicationHome);

    GUISession.getInstance().setUserApplicationHome(userApplicationHome);

  }

  /**
   * Description : initialize User settings Directory And Files method <br>
   */
  public static void initializeUserPreferenceDirectoryAndFiles() {
    // Create user Application folder if not exists
    final String userApplicationHome = GUISession.getInstance().getUserApplicationHome();
    final DirectoryFile userDirectory = new DirectoryFile(userApplicationHome);

    // Verify if application directory exists
    if (!userDirectory.exists()) {
      userDirectory.mkdirs();
    }

    // Create user settings file if not exists
    final String userConfigFileLocation = UtilApplication.getUserLocalSettingsPropertiesPath();
    final DirectoryFile userConfigFile = new DirectoryFile(userConfigFileLocation);

    if (!userConfigFile.exists()) {
      try {

        final InputStream defaultUserSettingsIs = UtilIO.getClasspathFileInputStream(UserPreferencesConstant.DEFAULT_USER_LOCAL_SETTINGS_FILE);
        UtilIO.copyFile(defaultUserSettingsIs, new FileOutputStream(userConfigFileLocation));

        // Define the default language of the user in the settings file
        final Properties defaultUserSettings = new Properties();

        defaultUserSettings.load(new FileInputStream(userConfigFileLocation));
        defaultUserSettings.setProperty(UserPreferencesConstant.USER_LANGUAGE_SETTING, I18nHelperApp.getI18nHelper().getUserLocale().getLanguage());

        defaultUserSettings.store(new FileOutputStream(userConfigFileLocation), null);
      } catch (final FileNotFoundException e) {
        throw new TechnicalException(e);
      } catch (final IOException e) {
        throw new TechnicalException(e);
      }
    }

  }

  /**
   * Description : initialize Logback <br>
   */
  public static void initializeLogback() {
    // assume SLF4J is bound to logback in the current environment
    final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

    try {
      final JoranConfigurator configurator = new JoranConfigurator();
      configurator.setContext(context);
      // Call context.reset() to clear any previous configuration, e.g. default
      // configuration. For multi-step configuration, omit calling context.reset().
      context.reset();

      // Convert String to inputStream
      final InputStream log4JIs = UtilIO.getClasspathFileInputStream("logback.xml");
      configurator.doConfigure(log4JIs);

    } catch (final Exception e) {
      throw new TechnicalException("Impossible to initialize Logback", e);
    }
    StatusPrinter.printInCaseOfErrorsOrWarnings(context);

  }

  /**
   * Description : get Application Name method <br>
   *
   * @return The application name of the application
   */
  public static String getApplicationName() {
    final String appName = I18nHelperApp.getMessage("application.name");
    return appName;
  }

  /**
   * Gets the application name short.
   *
   * @return the application name short
   */
  public static String getApplicationNameShort() {
    final String appName = I18nHelperApp.getMessage("application.name.short");
    return appName;
  }

  /**
   * Description : get Application Version method <br>
   *
   * @return The application version of the application
   */
  public static String getApplicationVersion() {
    final String appVersion = I18nHelperApp.getMessage("application.version");
    return appVersion;
  }

  /**
   * TODO Initialize preferences from database
   */
  public static void initializePreferences() {

    GUISessionProxy.getGuiSession().getPreferences().put("proxy.ip", "???");
    GUISessionProxy.getGuiSession().getPreferences().put("proxy.port", "???");
    GUISessionProxy.getGuiSession().getPreferences().put("proxy.user", "???");
    GUISessionProxy.getGuiSession().getPreferences().put("proxy.pwd", "???");
    GUISessionProxy.getGuiSession().getPreferences().put("screen.start.width", new Integer(1600));
    GUISessionProxy.getGuiSession().getPreferences().put("screen.start.height", new Integer(900));
    GUISessionProxy.getGuiSession().getPreferences().put("last.selected.folder.path", "G:/Download");
    GUISessionProxy.getGuiSession().getPreferences().put("last.selected.file.path", "G:/Download/file.txt");

  }

}
