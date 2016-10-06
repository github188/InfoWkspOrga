package com.sgu.infowksporga.jfx.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.gui.swing.util.UtilNotificationMsg;
import com.sgu.infowksporga.jfx.InfoWrkspOrgaFrame;
import com.sgu.infowksporga.jfx.perspective.AbstractPerspectivePanel;
import com.sgu.infowksporga.jfx.workspace.AbstractWorkspace;

import net.infonode.docking.View;

// TODO: Auto-generated Javadoc
/**
 * Description : GUISessionProxy class<br>.
 */
public class GUISessionProxy {

  /** The Class Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(GUISessionProxy.class);

  /** The Constant CURRENT_WORKSPACE. */
  public static final String CURRENT_WORKSPACE = "CURRENT_WORKSPACE";

  /**
   * The Constant CURRENT_PERSPECTIVE.
   * Where we display Workspace organization
   */
  public static final String CURRENT_PERSPECTIVE = "CURRENT_PERSPECTIVE";

  /**
   * The Constant CURRENT_VIEW.
   */
  public static final String CURRENT_VIEW = "CURRENT_VIEW";

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
   * Gets the info wrksp orga frame.
   *
   * @return the info wrksp orga frame
   */
  public static InfoWrkspOrgaFrame getInfoWrkspOrgaFrame() {
    return (InfoWrkspOrgaFrame) guiSession.getApplicationFrame();
  }

  /**
   * Sets the info wrksp orga frame.
   *
   * @param infoWrkspOrgaFrame the info wrksp orga frame
   */
  public static void setInfoWrkspOrgaFrame(final InfoWrkspOrgaFrame infoWrkspOrgaFrame) {
    guiSession.setApplicationFrame(infoWrkspOrgaFrame);
  }

  /**
   * Description : getCurrentWorkspace method <br>.
   *
   * @return The current workspace of the application
   */
  public static AbstractWorkspace getCurrentWorkspace() {
    return getInfoWrkspOrgaFrame().getWrkspSelected();
  }

  /**
   * Gets the current view.
   *
   * @return the current view
   */
  public static View getCurrentView() {
    return (View) guiSession.getSessionAttribute(CURRENT_VIEW);
  }

  /**
   * Sets the current view.
   *
   * @param view the current view
   */
  public static void setCurrentView(final View view) {
    guiSession.setSessionAttribute(CURRENT_VIEW, view);
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
    if ((isCurrentWorkspaceHasChanged() == null || isCurrentWorkspaceHasChanged() == false) && hasChanged) {
      UtilNotificationMsg.displayMessage("Workspace", "Le workspace a été modifié !");
    }
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
   * Gets the current perspective.
   *
   * @return the current perspective
   */
  public static AbstractPerspectivePanel getCurrentPerspective() {
    return getInfoWrkspOrgaFrame().getPnlPerspective();
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

}
