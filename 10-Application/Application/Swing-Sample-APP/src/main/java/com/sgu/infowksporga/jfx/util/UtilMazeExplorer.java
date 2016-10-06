package com.sgu.infowksporga.jfx.util;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.border.LineBorder;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.jidesoft.swing.JideSplitPaneDivider;
import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.pivot.AbstractIn;
import com.sgu.core.framework.util.UtilRest;
import com.sgu.core.framework.util.UtilString;
import com.sgu.core.framework.web.rest.http.GMediaType;
import com.sgu.infowksporga.jfx.InfoWrkspOrgaFrame;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import lombok.extern.slf4j.Slf4j;

/**
 * Description : UtilInfoWrkspOrga class<br>
 */
@Slf4j
public final class UtilInfoWrkspOrga {

  /**
   * Constructor<br>
   */
  private UtilInfoWrkspOrga() {
  }

  /**
   * Description : get User Local Settings Properties Path method <br>
   * It is used to Load local properties but also to save modified values
   *
   * @return The local path location of the properties file
   */
  public static String getUserLocalSettingsPropertiesPath() {
    final String userHome = GUISession.getInstance().getUserApplicationHome();
    final String userConfigFileLocation = userHome + "/" + UserPreferencesConstant.USER_LOCAL_SETTINGS_FILE;

    log.debug("User settings properties file location : '{}'", userConfigFileLocation);

    return userConfigFileLocation;
  }

  /**
   * Sets the info wrksp orga app icon.
   *
   * @param window the info wrksp orga app icon
   */
  public static void setInfoWrkspOrgaAppIcon(final Window window) {
    window.setIconImage(Toolkit.getDefaultToolkit().getImage(InfoWrkspOrgaFrame.class.getResource("/icons/infowrksporga-ico.png")));
  }

  /**
   * Description : updateUserSettings method <br>
   */
  public static void updateUserSettingsOnDisk() {
    final String userConfigFileLocation = getUserLocalSettingsPropertiesPath();
    try {
      GUISession.getInstance().getUserLocalSettings().store(new FileOutputStream(userConfigFileLocation), null);
    } catch (final Exception e) {
      throw new TechnicalException("Impossible to save user language in local settings file : '" + userConfigFileLocation + "'");
    }
  }

  /**
   * Description : replaceByUserCode method <br>
   *
   * @param str the string with user variable to replace
   * @return the modified string
   */
  public static String replaceByUserCode(final String str) {
    if (str != null) {
      return UtilString.replaceString(str, "${User}", GUISessionProxy.getGuiSession().getCurrentUser());
    }

    return null;
  }

  /**
   * Description : getJDesktopURI method <br>
   *
   * @param file The file path to convert
   * @return The corresponding URI
   */
  public static URI getJDesktopURI(String file) {

    URI uri = null;

    try {

      if (!file.contains("http") && !file.contains("www")) {
        file = file.replace("file:", "");
        final File curFile = new File(file);
        uri = new URI(curFile.toURI().toString().replace("file:////", "file://"));
      }
      else {
        uri = new URI(file);
      }

    } catch (final URISyntaxException e) {
      throw new TechnicalException(e);
    }

    return uri;
  }

  /**
   * Description : displayWithDesktopTool method <br>
   *
   * @param uriPath the uri of file to open
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "error.desktop.open.document",
                value = "Impossible d'ouvrir le document souhaité.<br/>Veuillez vérifier l'URL utilisée.<br/><b>{0}</b>"), // Force \n
  })
  public static void displayWithDesktopTool(final String uriPath) {
    final Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          final URI uri = UtilInfoWrkspOrga.getJDesktopURI(uriPath);
          Desktop.getDesktop().browse(uri);
        } catch (final Exception exp) {
          UtilDlgMessage.error(I18nHelperApp.getMessage("error.desktop.open.document", uriPath), "");
        }
      }
    });
    thread.start();
  }

  /**
   * Description : setDividerColor method <br>
   *
   * @param divider
   */
  public static void setDividerColor(final JideSplitPaneDivider divider) {
    divider.setOpaque(true);
    divider.setBackground(new Color(231, 252, 158));
    divider.setBorder(new LineBorder(new Color(191, 219, 255)));
  }

  /**
   * Call rest business service.
   *
   * @param URI the uri
   * @param pivotIn the pivot in
   * @param pivotOutType the pivot out type
   * @param method the method
   * @param contentType the content type
   * @param responseMediaType the response media type
   * @return the object
   */
  public static Object callRestBusinessService(final String URI, final AbstractIn pivotIn, final Class<?> pivotOutType,
  final HttpMethod method, MediaType contentType, final MediaType... responseMediaType) {
    log.debug("\n==> Call Rest service with :\nURI = '{}'\nPivotIn = '{}'\nPivotOutType = '{}', responseMediaType = '{}'", URI,
              pivotIn != null ? pivotIn.getClass().getName() : "null", pivotOutType.getName(), responseMediaType);
    return UtilRest.callRestBusinessService(URI, pivotIn, pivotOutType, method, contentType, responseMediaType);
  }

  /**
   * Call rest business service.
   *
   * @param URI the uri
   * @param pivotIn the pivot in
   * @param pivotOutType the pivot out type
   * @return the object
   */
  public static Object callRestBusinessService(final String URI, final AbstractIn pivotIn, final Class<?> pivotOutType) {
    return UtilInfoWrkspOrga.callRestBusinessService(URI, pivotIn, pivotOutType, HttpMethod.POST,
                                                     GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT,
                                                     GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT);
  }

}