package com.sgu.infowksporga.jfx.util;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.sgu.core.framework.gui.jfx.util.GUISession;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.core.framework.resources.EnvironmentEnum;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.jfx.main.Application;
import com.sgu.infowksporga.jfx.perspective.cbb.CbbPerspectiveItemVo;
import com.sgu.infowksporga.jfx.perspective.mvc.PerspectivePanelScreen;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTreeItem;

/**
 * Description : GUISessionProxy class<br>.
 */

public class GUISessionProxy {

  /**
   * The Constant ENVIRONMENT.
   * Define by -Denvironement=Development;Production;...
   */
  public static final String ENVIRONMENT = "ENVIRONMENT";

  /**
   * The Constant CURRENT_VIEW.
   */
  public static final String CARROUSEL_IMAGES = "CARROUSEL_IMAGES";

  /** The Key for current Workspace loading errors in session (URL elapsed most of the time). */
  private static final String WORKSPACE_LOADING_RESULT = "WORKSPACE_LOADING_RESULT";

  /** The Key to know if current workspace has changed. */
  private static final String CURRENT_WORKSPACE_HAS_CHANGED = "CURRENT_WORKSPACE_HAS_CHANGED";

  /** The Key for Last selected file or directory selected in Directory Desk view stored in session. */
  private static final String LAST_SELECTED_FILE = "LAST_SELECTED_FILE";

  /** The Key for Last file edition mode (CUT / COPY). */
  private static final String LAST_FILE_EDITION_MODE = "LAST_FILE_EDITION_MODE";

  /** The Constant APPLICATION_DATE_TIME_TO_STRING_FORMATTER. */
  private static DateTimeFormatter APPLICATION_DATE_TIME_TO_STRING_FORMATTER;

  /** The gui session. */
  private final static GUISession guiSession = GUISession.getInstance();

  /**
   * Constructor<br>.
   */
  private GUISessionProxy() {
  }

  /**
   * Gets the gui session.
   *
   * @return the guiSession : See field description
   * @see #guiSession
   */
  public static GUISession getGuiSession() {
    return guiSession;
  }

  /**
   * Gets the current application.
   *
   * @return the current application
   */
  public static Application getApplication() {
    return (Application) guiSession.getApplication();
  }

  /**
   * Description : getCarrouselImages method <br>.
   *
   * @return The carrousel images to be displayed from perspective tree selection
   */
  @SuppressWarnings("unchecked")
  public static List<ImageIcon> getCarrouselImages() {
    return (List<ImageIcon>) getGuiSession().getSessionAttribute(CARROUSEL_IMAGES);
  }

  /**
   * Description : setCarrouselImages method <br>.
   *
   * @param carrouselImages The carrousel images
   */
  public static void setCarrouselImages(final List<ImageIcon> carrouselImages) {
    getGuiSession().setSessionAttribute(CARROUSEL_IMAGES, carrouselImages);
  }

  /**
   * Description : getCurrentWorkspace method <br>.
   *
   * @return The current workspace of the application
   */
  public static PerspectiveTreeItem getCurrentWorkspace() {
    final PerspectiveTreeItem item = (PerspectiveTreeItem) getApplication().getApplicationScreen().getPerspectiveScreen().view().getTreeWorkspaces().getSelectionModel()
                                                                           .getSelectedItem();
    return item;
  }

  /**
   * Gets the application date time formatter.
   * Used to display date on screen
   *
   * @return the application date time formatter
   */
  public static DateTimeFormatter getApplicationDateTimeFormatter() {
    if (APPLICATION_DATE_TIME_TO_STRING_FORMATTER == null) {
      APPLICATION_DATE_TIME_TO_STRING_FORMATTER = DateTimeFormatter.ofPattern(I18NConstant.getDateHourMinuteFormat());
    }
    return APPLICATION_DATE_TIME_TO_STRING_FORMATTER;
  }

  /**
   * Gets the current view.
   *
   * @return the current view
   */
  public static Object getCurrentView() {
    return null;//guiSession.getSessionAttribute(CURRENT_VIEW);
  }

  /**
   * Description : isCurrentWorkspaceHasChanged method <br>.
   *
   * @return True if the current workspace has changed
   */
  public static Boolean isCurrentWorkspaceHasChanged() {
    return (Boolean) guiSession.getSessionAttribute(CURRENT_WORKSPACE_HAS_CHANGED);
  }

  /**
   * Description : setCurrentWorkspaceHasChanged method <br>.
   *
   * @param hasChanged True or False current connected user
   */
  public static void setCurrentWorkspaceHasChanged(final Boolean hasChanged) {
    /*
     * if ((isCurrentWorkspaceHasChanged() == null || isCurrentWorkspaceHasChanged() == false) && hasChanged) {
     * UtilNotificationMsg.displayMessage("Workspace", "Le workspace a été modifié !");
     * }
     */
    guiSession.setSessionAttribute(CURRENT_WORKSPACE_HAS_CHANGED, hasChanged);
  }

  /**
   * Description : getWorkspaceLoadingResult method <br>.
   *
   * @return The workspace loading result
   */
  @SuppressWarnings("unchecked")
  public static List<String> getWorkspaceLoadingResult() {

    List<String> result = (List<String>) guiSession.getSessionAttribute(WORKSPACE_LOADING_RESULT);

    if (result == null) {
      result = new ArrayList<String>(5);
      guiSession.setSessionAttribute(WORKSPACE_LOADING_RESULT, result);
    }

    return result;
  }

  /**
   * Gets the current perspective Screen
   *
   * @return the current perspective Screen
   */
  public static PerspectivePanelScreen getPerspectiveScreen() {
    return getApplication().getApplicationScreen().getPerspectiveScreen();
  }

  /**
   * Gets the selected perspective entity.
   *
   * @return the selected perspective entity
   */
  public static Perspective getCurrentPerspectiveEntity() {
    final CbbPerspectiveItemVo itemVo = (CbbPerspectiveItemVo) getPerspectiveScreen().view().getCbbPerspective().getSelectionModel().getSelectedItem();
    return itemVo.getPerspective();
  }

  /**
   * Description : getLastSelectedFile method <br>.
   *
   * @return The Last selected File in WorkSpace
   */
  public static File getLastSelectedFile() {
    return (File) getGuiSession().getSessionAttribute(LAST_SELECTED_FILE);
  }

  /**
   * Description : setLastSelectedFile method <br>.
   *
   * @param file The Last selected file from Directory view
   */
  public static void setLastSelectedFile(final File file) {
    getGuiSession().setSessionAttribute(LAST_SELECTED_FILE, file);
  }

  /**
   * Description : getLastFileEditionMode method <br>.
   *
   * @return The Last file edition mode
   */
  public static String getLastFileEditionMode() {

    return (String) getGuiSession().getSessionAttribute(LAST_FILE_EDITION_MODE);
  }

  /**
   * Description : setLastFileEditionMode method <br>.
   *
   * @param mode The Last file edition mode (CUT/COPY)
   */
  public static void setLastFileEditionMode(final String mode) {
    getGuiSession().setSessionAttribute(LAST_FILE_EDITION_MODE, mode);
  }

  /**
   * Sets the environment.
   *
   * @param env the environment
   */
  public static void setEnvironment(final EnvironmentEnum env) {
    getGuiSession().setSessionAttribute(ENVIRONMENT, env);
  }

  /**
   * Gets the environment.
   *
   * @return the environment
   */
  public static EnvironmentEnum getEnvironment() {

    return (EnvironmentEnum) getGuiSession().getSessionAttribute(ENVIRONMENT);
  }

}
