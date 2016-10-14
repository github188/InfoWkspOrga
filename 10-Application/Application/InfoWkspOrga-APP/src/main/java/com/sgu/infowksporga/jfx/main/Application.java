package com.sgu.infowksporga.jfx.main;

import java.net.URL;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.screen.AGApplication;
import com.sgu.core.framework.gui.jfx.util.UtilGUI;
import com.sgu.core.framework.resources.EnvironmentEnum;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.main.ApplicationPreloader.MessageProgressNotification;
import com.sgu.infowksporga.jfx.main.action.ExitAction;
import com.sgu.infowksporga.jfx.main.ui.ApplicationScreen;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * The class <strong>Application Information Workspace Organizer</strong>.
 *
 * @author
 */
@Slf4j
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = "application.name", value = "Information Workspace Organizer"), // Force /n
              @I18nProperty(key = "application.name.short", value = "Info-Wksp-Orga"), // Force /n 
              @I18nProperty(key = "application.version", value = "1.0.0"), // Force /n
})
//@PomVersion(value = "1.0.0", update = "true")
@Getter
public final class Application extends AGApplication<StackPane> {

  /** The application screen. */
  private ApplicationScreen applicationScreen;

  /**
   * Application launcher.
   *
   * @param args the command line arguments
   */
  public static void main(final String... args) {
    preloadAndLaunch(ApplicationPreloader.class, args);
  }

  /** {@inheritDoc} */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "application.preload.start", value = "Démarage de l'initialialisation de l'application --> OK"), // Force /n
                @I18nProperty(key = "application.preload.end", value = "Fin de l'initialialisation de l'application --> OK"), // Force /n 
                @I18nProperty(key = "application.preload.gui.session.set.app", value = "Mise en cache de l'application (GuiSession) --> OK"), // Force /n
                @I18nProperty(key = "application.preload.logbak.init.ok", value = "Configuration des logs --> OK"), // Force /n 
                @I18nProperty(key = "application.preload.spring.config.ok", value = "Configuration de Spring --> OK"), // Force /n
                @I18nProperty(key = "application.preload.remote.connection.ok", value = "Contrôle des connexions externes --> OK"), // Force /n
                @I18nProperty(key = "application.preload.carroussel.img.load.ok", value = "Chargement des images du carroussel --> OK"), // Force /n
                @I18nProperty(key = "application.preload.user.app.home.ok", value = "Définition du répertoire 'home' de l'utilisateur --> OK"), // Force /n 
                @I18nProperty(key = "application.preload.user.pref.init.ok", value = "Création du répertoire pour les prefs utilisateurs --> OK"), // Force /n 
                @I18nProperty(key = "application.preload.user.local.settings.ok", value = "Initialisation du fichier pour les prefs utilisateurs --> OK"), // Force /n 
                @I18nProperty(key = "application.preload.user.language.ok", value = "Chargement de la langue préférée de l'utilisateur --> OK"), // Force /n
                @I18nProperty(key = "application.preload.exception.handler.ok", value = "Chargement des gestionnaires d'exception --> OK"), // Force /n
                @I18nProperty(key = "application.preload.jvm.property.ko",
                value = "Environment property is not define in JVM argument : -Denvironment=Dev or -Denvironment=production --> KO"), // Force /n

  })
  public void init() throws Exception {
    // Store the main application in session
    GUISessionProxy.getGuiSession().setApplication(this);

    try {
      double progress = 0.10d;

      notifyPreloader(new MessageProgressNotification(progress, "application.preload.start")); // Initializing

      super.init();

      // Store the working environment in session (-Denvironment=Development) ignore case
      final String env = System.getProperty("environment");
      EnvironmentEnum environment = EnvironmentEnum.getEnumForType(env);
      if (environment == null) {
        environment = EnvironmentEnum.getEnumForShortType(env);
      }
      if (environment == null) {
        throw new TechnicalException(I18nHelperApp.getMessage("application.preload.jvm.property.ko"));
      }
      GUISessionProxy.setEnvironment(environment);
      progress += 0.10;
      notifyPreloader(new MessageProgressNotification(progress, "application.preload.gui.session.set.app"));

      // Initialize other services
      ApplicationInitializer.initializeLogback();
      progress += 0.10;
      notifyPreloader(new MessageProgressNotification(progress, "application.preload.logbak.init.ok"));

      ApplicationInitializer.initializeSpringConfiguration();
      progress += 0.10;
      notifyPreloader(new MessageProgressNotification(progress, "application.preload.spring.config.ok"));

      //ApplicationInitializer.verifyAllRemoteConnexionAreAvailable();
      progress += 0.10;
      notifyPreloader(new MessageProgressNotification(progress, "application.preload.remote.connection.ok"));

      //ApplicationInitializer.initializeCarrouselImages();
      progress += 0.10;
      notifyPreloader(new MessageProgressNotification(progress, "application.preload.carroussel.img.load.ok"));

      ApplicationInitializer.initializeUserApplicationHome();
      progress += 0.10;
      notifyPreloader(new MessageProgressNotification(progress, "application.preload.user.app.home.ok"));

      ApplicationInitializer.initializeUserPreferenceDirectoryAndFiles();
      progress += 0.10;
      notifyPreloader(new MessageProgressNotification(progress, "application.preload.user.pref.init.ok"));

      ApplicationInitializer.initializeUserLocalSettingsProperties();
      progress += 0.10;
      notifyPreloader(new MessageProgressNotification(progress, "application.preload.user.local.settings.ok"));

      ApplicationInitializer.initializeApplicationLanguage();
      progress += 0.01;
      notifyPreloader(new MessageProgressNotification(progress, "application.preload.user.language.ok"));

      // Attach exception handlers
      initializeExceptionHandler();
      progress += 0.01;
      notifyPreloader(new MessageProgressNotification(progress, "application.preload.exception.handler.ok"));

      progress = 1.0;
      notifyPreloader(new MessageProgressNotification(1.0, "application.preload.end"));  // Starting

      // Finish by user authentification
      ApplicationInitializer.authenticateUser();

    } catch (final Exception e) {
      log.error(e.getMessage(), e);
      throw new TechnicalException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void customizeStage(final Stage stage) {
    stage.setFullScreen(false);
    stage.getIcons().add(UtilGUI.getImage("/icons/app/application-ico.png"));
    buildApplicationTitle(stage);

    stage.setOnCloseRequest(e -> {
      // Get the Action associated with the Exit Button
      final ExitAction action = (ExitAction) getActionManager().getActions().get(applicationScreen.getView().getBtnExit());
      action.handle(null);
      e.consume();
    });
  }

  /**
   * Builds the application title.
   *
   * @param stage the stage
   */
  protected void buildApplicationTitle(final Stage stage) {
    stage.setTitle(ApplicationInitializer.getApplicationName() + " - V" + ApplicationInitializer.getApplicationVersion() + " ---> "
                   + GUISessionProxy.getEnvironment().getType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void customizeScene(final Scene scene) {
    addCSS(scene, "/com/sgu/infowksporga/jfx/main/ui/application.css");

    applicationScreen = new ApplicationScreen();
    applicationScreen.initMVC();

    do {
      // Check the return of FXML screen build is finished before call MVC builder
      if (applicationScreen.view().isViewInitialized()) {
        scene.setRoot(applicationScreen.getViewRoot());
      }
    } while (applicationScreen.view().isViewInitialized() == false);

  }

  /**
   * Attach a new CSS file to the scene using the default classloader.
   *
   * @param scene the scene that will hold this new CSS file
   * @param styleSheetItem the stylesheet item to add
   */
  @Override
  protected void addCSS(final Scene scene, final String styleSheetLocation) {

    final URL styleSheetURL = this.getClass().getResource(styleSheetLocation);
    if (styleSheetURL == null) {
      throw new TechnicalException("StyleSheet '" + styleSheetLocation + "' not found");
    }
    else {
      scene.getStylesheets().add(styleSheetURL.toExternalForm());
    }

  }

}
