package com.sgu.infowksporga.jfx.views;

import java.awt.event.MouseEvent;

import com.sgu.core.framework.gui.swing.docking.GWindowDragger;
import com.sgu.infowksporga.jfx.util.UtilWorkspace;

import net.infonode.docking.DockingWindow;
import net.infonode.docking.RootWindow;

/**
 * Description : Project Manager Window Dragger class<br>
 * Used to identify view drop user action and set Workspace has dirty
 */
public class WindowDragger extends GWindowDragger {

  /**
   * Constructor<br>
   *
   * @param dragWindow
   */
  public WindowDragger(final DockingWindow dragWindow) {
    super(dragWindow);
  }

  /**
   * Constructor<br>
   *
   * @param dragWindow
   * @param rootWindow
   */
  public WindowDragger(final DockingWindow dragWindow, final RootWindow rootWindow) {
    super(dragWindow, rootWindow);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dropWindow(final MouseEvent mouseEvent) {
    super.dropWindow(mouseEvent);
    UtilWorkspace.workspaceIsModified();

    // window drop can impact view layout update tab listener
    UtilWorkspace.updateWorkspaceTabsListener();
  }

}
