package com.sgu.infowksporga.jfx.zfacade.remote._init;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.ProgressMonitor;
import javax.swing.UIManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.GFrame;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.GUIManager;
import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;
import com.sgu.core.framework.gui.swing.event.EventQueueManager;
import com.sgu.core.framework.gui.swing.i18n.I18nHelperSwing;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.i18n.I18nHelper;
import com.sgu.core.framework.i18n.I18nHelperFwk;
import com.sgu.core.framework.i18n.LocalI18nService;
import com.sgu.core.framework.io.DirectoryFile;
import com.sgu.core.framework.pivot.AbstractOut;
import com.sgu.core.framework.spring.ReloadableResourceBundleMessageSource;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.core.framework.spring.service.remote.IGetRemoteSpringObjectService;
import com.sgu.core.framework.util.Properties;
import com.sgu.core.framework.util.UtilDate;
import com.sgu.core.framework.util.UtilIO;
import com.sgu.infowksporga.business.xml.jaxb.menu.MenuApplication;
import com.sgu.infowksporga.business.xml.jaxb.toolbar.ToolbarApplication;
import com.sgu.infowksporga.jfx.InfoWrkspOrgaFrame;
import com.sgu.infowksporga.jfx.MenuManager;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.menu.action.file.ExitAction;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UserPreferencesConstant;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.zfacade.AbstractBusinessFacade;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * The Class Application Initializer Facade.
 */
@Service
public class ApplicationInitializerFacade extends AbstractBusinessFacade<AbstractOut, Container> {

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInitializerFacade.class);

  /**
   * The prez service object to access external JWS Configuration
   */
  @Autowired
  private IGetRemoteSpringObjectService prezService;

  /**
   * The business service to verify database is available
   * 
   * @Autowired
   *            private IDatabaseAccessibleVerifierBo databaseAccessibleVerifierBo;
   */
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractOut execute(final Container businessContainer) throws TechnicalException, BusinessException {
    GUISession.getInstance().getApplicationFrame().setDefaultCloseOperation(GFrame.DO_NOTHING_ON_CLOSE);
    intializeDefaultLookAndFeel();

    verifyAllRemoteConnexionAreAvailable();

    initializeApplicationLookAndFeel();
    initializeI18nMessageBundle();

    initializeApplicationMenu();
    initializeApplicationToolbar();

    initializeLogback();
    initializeApplicationTitle();
    initializeUserApplicationHome();
    initializeCarrouselImages();

    initializeUserPreferenceDirectoryAndFiles();
    initializeUserLocalSettingsProperties();
    initializeApplicationLanguage();

    initializeEventQueueManager();
    initializeApplicationExitManagement();

    return null;
  }

  /**
   * Description : initializeApplicationToolbar method <br>
   */
  private void initializeApplicationToolbar() {

    // Convert XML configuration with JAXB
    try {
      final ClassLoader cl = com.sgu.infowksporga.business.xml.jaxb.toolbar.ObjectFactory.class.getClassLoader();
      final JAXBContext jc = JAXBContext.newInstance("com.sgu.infowksporga.business.xml.jaxb.toolbar", cl);
      final Unmarshaller unmarshaller = jc.createUnmarshaller();
      final InputStream is = UtilIO.getClasspathFileInputStream("toolbar-application.xml");
      final ToolbarApplication configuration = (ToolbarApplication) unmarshaller.unmarshal(is);
      MenuManager.toolbarApplication = configuration;
    } catch (final JAXBException e) {
      LOGGER.error(e.getMessage(), e);
      throw new TechnicalException("infowrksporga.error.menu.configuration.bad.xml", e);
    }

  }

  /**
   * Description : initializeApplicationMenu method <br>
   */
  private void initializeApplicationMenu() {

    // Convert XML configuration with JAXB
    try {
      final ClassLoader cl = com.sgu.infowksporga.business.xml.jaxb.menu.ObjectFactory.class.getClassLoader();
      final JAXBContext jc = JAXBContext.newInstance("com.sgu.infowksporga.business.xml.jaxb.menu", cl);
      final Unmarshaller unmarshaller = jc.createUnmarshaller();
      final InputStream is = UtilIO.getClasspathFileInputStream("menu-application.xml");
      final MenuApplication configuration = (MenuApplication) unmarshaller.unmarshal(is);
      MenuManager.menuApplication = configuration;
    } catch (final JAXBException e) {
      LOGGER.error(e.getMessage(), e);
      throw new TechnicalException("infowrksporga.error.toolbar.configuration.bad.xml", e);
    }

  }

  /**
   * Description : initializeCarrouselImages method <br>
   * Store all the carrousel images in session
   */
  private void initializeCarrouselImages() {
    List<ImageIcon> carrouselImages = prezService.getCarrouselImages();;
    GUISessionProxy.setCarrouselImages(carrouselImages);
  }

  /**
   * Description : intializeDefaultLookAndFeel method <br>
   */
  private void intializeDefaultLookAndFeel() {
    final Properties localLookAndFeelStyleProperties = new Properties("/default-lookAndFeel-Style.properties");

    // By default load the default (in case of exception to access remote server)
    try {
      GUIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel", localLookAndFeelStyleProperties);
    } catch (final Exception e) {
      throw new TechnicalException(e);
    }
  }

  /**
   * Description : verifyAllRemoteConnexionAreAvailable method <br>
   */
  private void verifyAllRemoteConnexionAreAvailable() {
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
      LOGGER.error(e.getMessage(), e);
      UtilDlgMessage.error("Le serveur de 'présentation' est inaccessible\n" + prezStrUrl + "\nVeuillez contacter l'administrateur \n"
                           + "en joignant la copie du texte contenue dans le bloc détail\n" + "Merci.", e, 500);
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
      LOGGER.error(e.getMessage(), e);
      UtilDlgMessage.error("Le serveur 'business' est inaccessible\n" + bizStrUrl + "\nVeuillez contacter l'administrateur \n"
                           + "en joignant la copie du texte contenue dans le bloc détail\n" + "Merci.", e, 500);
      System.exit(0);
    }

    // Verify Database is accessible
    try {
      //databaseAccessibleVerifierBo.checkDatabaseIsAvailable();
    } catch (final TechnicalException e1) {
      LOGGER.error(e1.getMessage(), e1);

      try { // try a second time
        UtilDate.delay(5000);
        //databaseAccessibleVerifierBo.checkDatabaseIsAvailable();
      } catch (final TechnicalException e2) {
        LOGGER.error(e2.getMessage(), e2);
        UtilDlgMessage.error("Le serveur de 'base de données' est inaccessible\n" + "Veuillez contacter l'administrateur \n"
                             + "en joignant la copie du texte contenue dans le bloc détail\n" + "Merci.", e2);
        System.exit(0);
      }

    }
  }

  /**
   * Description : initialize Application Look And Feel method <br>
   */
  private void initializeApplicationLookAndFeel() {

    // Now try to load the remote one
    final Properties remoteLookAndFeelStyleProperties = new Properties("/lookAndFeel-Style.properties");
    if (remoteLookAndFeelStyleProperties == null) {
      throw new TechnicalException("lookAndFeel-Style.properties file is not defined in presentation");
    }

    // Initialize look & feel with the remote one
    try {
      GUIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel", remoteLookAndFeelStyleProperties);
    } catch (final Exception e) {
      throw new TechnicalException(e);
    }

    // Define Combo box UI (On Win 7 default one d'ont work with invalid background
    UIManager.put("ComboBoxUI", "javax.swing.plaf.basic.BasicComboBoxUI");

  }

  /**
   * Description : manage Application Exit (click on close button of the frame) <br>
   */
  private void initializeApplicationExitManagement() {
    final InfoWrkspOrgaFrame frame = (InfoWrkspOrgaFrame) GUISession.getInstance().getApplicationFrame();
    frame.addWindowListener(new WindowAdapter() {

      /** {@inheritDoc} */
      @Override
      public void windowClosing(final WindowEvent e) {
        final ExitAction exitAction = new ExitAction();
        // Simulate a press on exit menu
        exitAction.actionPerformed(new ActionEvent(new GLabel(), 0, ""));
      }
    });
  }

  /**
   * Description : Define our Event queue to manage rule and Non catched exception
   */
  private void initializeEventQueueManager() {
    final EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
    queue.push(new EventQueueManager(GUISession.getInstance().getApplicationFrame()));
  }

  /**
   * Description : initialize Application Language method <br>
   */
  private void initializeApplicationLanguage() {
    Locale locale = null;

    // Look in JVM Parameters first
    /*
     * String strLocale = ConfigurationService.getInstance().getParameter(ConfigurationService.LOCALE); if (strLocale != null) {
     * locale = new Locale(strLocale); }
     */
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
  private void initializeUserLocalSettingsProperties() {

    final Properties userLocalSettings = new Properties();
    final String userConfigFileLocation = UtilInfoWrkspOrga.getUserLocalSettingsPropertiesPath();
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
  private void initializeUserApplicationHome() {

    final String appVersion = getApplicationVersion();

    final String userApplicationHome = System.getProperty("user.home") + "/Application Data/" + getApplicationName() + "/" + appVersion;

    LOGGER.debug("La localisation du fichier de preferences utilisateur : '{}'", userApplicationHome);

    GUISession.getInstance().setUserApplicationHome(userApplicationHome);

  }

  /**
   * Description : initialize User settings Directory And Files method <br>
   */
  private void initializeUserPreferenceDirectoryAndFiles() {
    // Create user Application folder if not exists
    final String userApplicationHome = GUISession.getInstance().getUserApplicationHome();
    final DirectoryFile userDirectory = new DirectoryFile(userApplicationHome);

    // Verify if application directory exists
    if (!userDirectory.exists()) {
      userDirectory.mkdirs();
    }

    // Create user settings file if not exists
    final String userConfigFileLocation = UtilInfoWrkspOrga.getUserLocalSettingsPropertiesPath();
    final DirectoryFile userConfigFile = new DirectoryFile(userConfigFileLocation);

    if (!userConfigFile.exists()) {
      try {

        final InputStream defaultUserSettingsIs = UtilIO.getClasspathFileInputStream(UserPreferencesConstant.DEFAULT_USER_LOCAL_SETTINGS_FILE);
        UtilIO.copyFile(defaultUserSettingsIs, new FileOutputStream(userConfigFileLocation));

        // Define the default language of the user in the settings file
        final Properties defaultUserSettings = new Properties();

        defaultUserSettings.load(new FileInputStream(userConfigFileLocation));
        defaultUserSettings.setProperty(UserPreferencesConstant.USER_LANGUAGE_SETTING,
                                        I18nHelperApp.getI18nHelper().getUserLocale().getLanguage());

        defaultUserSettings.store(new FileOutputStream(userConfigFileLocation), null);
      } catch (final FileNotFoundException e) {
        throw new TechnicalException(e);
      } catch (final IOException e) {
        throw new TechnicalException(e);
      }
    }

  }

  /**
   * Description : initialize Internationalization Message Bundle method <br>
   */
  @SuppressWarnings("unchecked")
  private void initializeI18nMessageBundle() {
    // Get I18N files content from presentation server (Shared lib)

    final I18nHelper i18nHelper = new I18nHelper();

    final LocalI18nService i18nService = new LocalI18nService();
    i18nService.getDefinedLocale().add("fr");
    i18nService.getDefinedLocale().add("en");

    final ReloadableResourceBundleMessageSource rrbms = new ReloadableResourceBundleMessageSource();
    rrbms.setResourceLoader(new DefaultResourceLoader(I18nHelperFwk.class.getClassLoader()));
    rrbms.setBasenames("i18n/fwk-core", "i18n/fwk-core-field-format", "i18n/fwk-core-validation", "i18n/swing_framework_message",
                       "i18n/infowrksporga_message", "i18n/gfi_message", "i18n/application-prez", "i18n/application-biz",
                       "i18n/application-common", "spring/server-localization");
    i18nService.setResourceBundleMessageSource(rrbms);

    i18nHelper.setI18nService(i18nService);

    I18nHelperFwk.init(i18nHelper);
    I18nHelperSwing.init(i18nHelper);
    I18nHelperApp.init(i18nHelper);
  }

  /**
   * Description : initialize Logback <br>
   */
  private void initializeLogback() {
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
   * Description : initialize Application Title method <br>
   */
  private void initializeApplicationTitle() {
    GUISession.getInstance().getApplicationFrame().setTitle(getApplicationName() + " / " + getApplicationVersion());
  }

  /**
   * Description : get Application Name method <br>
   * 
   * @return The application name of the application
   */
  private String getApplicationName() {
    final String appName = I18nHelperApp.getMessage("application.name");
    return appName;
  }

  /**
   * Description : get Application Version method <br>
   * 
   * @return The application version of the application
   */
  private String getApplicationVersion() {
    final String appVersion = I18nHelperApp.getMessage("application.version");
    return appVersion;
  }

  /** {@inheritDoc} */
  @Override
  public void refreshScreen(AbstractOut output, Container container, StringBuilder reportMessages, ProgressMonitor monitor) {
    // do nothing
  }

}
