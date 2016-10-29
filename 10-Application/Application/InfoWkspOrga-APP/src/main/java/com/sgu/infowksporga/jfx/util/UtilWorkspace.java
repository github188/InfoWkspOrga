package com.sgu.infowksporga.jfx.util;

import org.dockfx.DockPos;

import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.util.UtilDockFX;

/**
 * The Class UtilWorkspace.
 */
public class UtilWorkspace {

  /**
   * The Constructor.
   */
  private UtilWorkspace() {
  }

  /**
   * Adds the dock pane view.
   *
   * @param model the model
   * @param dockPos the dock pos
   */
  public final static void showDefaultDockPane() {
    try {
      // Reinit Dockpane
      GUISessionProxy.getApplication().getApplicationScreen().controller().displayNewEmptyWorkspace();

      UtilView.addDockPaneView(UtilView.buildDefaultViewModel(), DockPos.RIGHT, null);

    } catch (final Exception e) {
      throw new TechnicalException(e);
    }
  }

  /**
   * Show empty dock pane.
   */
  public static void showEmptyDockPane() {
    // Reinit Dockpane
    GUISessionProxy.getApplication().getApplicationScreen().controller().displayNewEmptyWorkspace();

  }

  /**
   * Builds the xml dock.
   *
   * @return the string
   */
  public static String buildXmlDock() {
    final GDockPane dockPane = GUISessionProxy.getApplication().getApplicationScreen().getView().getDockPane();
    final String xmlDock = UtilDockFX.serializeDockFxStructureToXmlString(dockPane);

    return xmlDock;
  }

}
