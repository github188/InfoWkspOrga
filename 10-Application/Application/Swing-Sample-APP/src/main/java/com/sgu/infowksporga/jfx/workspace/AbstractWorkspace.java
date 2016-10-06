package com.sgu.infowksporga.jfx.workspace;

import com.sgu.core.framework.gui.swing.docking.GDockingUtil;
import com.sgu.core.framework.gui.swing.docking.GRootWindow;
import com.sgu.infowksporga.business.dto.WorkspaceDto;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilWorkspace;

import net.infonode.docking.DockingWindow;
import net.infonode.docking.DockingWindowAdapter;
import net.infonode.docking.View;
import net.infonode.docking.properties.RootWindowProperties;
import net.infonode.docking.theme.DockingWindowsTheme;
import net.infonode.docking.theme.ShapedGradientDockingTheme;
import net.infonode.docking.util.PropertiesUtil;
import net.infonode.docking.util.ViewMap;
import net.infonode.util.Direction;

/**
 * Description : AbstractWorkspace class<br>
 */
public abstract class AbstractWorkspace {

  /**
   * The Worksapce serializer
   */
  public final static WorkspaceSerializer WORKSPACE_SERIALIZER = new WorkspaceSerializer();

  /**
   * The one and only root window
   */
  protected GRootWindow rootWindow;

  /**
   * The Workspace configuration
   */
  protected WorkspaceDto workspaceDto;

  /**
   * In this properties object the modified property values for close buttons etc. are stored. This object is cleared when the
   * theme is changed.
   */
  protected RootWindowProperties rootDockingWindowProperties;

  /**
   * The currently applied docking windows theme
   */
  protected DockingWindowsTheme dockingWindowTheme;

  /**
   * Contains all the views
   */
  protected ViewMap viewsMap;

  /**
   * Constructor<br>
   *
   * @param configuration db workspace config
   */
  public AbstractWorkspace(final WorkspaceDto configuration) {
    super();
    this.workspaceDto = configuration;

    buildWorkspace();
  }

  /**
   * Description : init method <br>
   * do some initialization before building workspace start
   */
  public void init() {
    WORKSPACE_SERIALIZER.setWorkspaceDto(workspaceDto);
  }

  /**
   * Description : buildWorkspace method <br>
   */
  protected void buildWorkspace() {
    init();
    initDockingViewMap();
    buildDockingWindow();
    defineWorkspaceLayout();

  }

  /**
   * Description : defineListener method <br>
   */
  public void defineListener() {

    final DockingWindowAdapter adapter = new DockingWindowAdapter() {

      /**
       * {@inheritDoc}
       */
      @Override
      public void viewFocusChanged(final View previouslyFocusedView, final View focusedView) {
        if (focusedView != null) {
          GUISessionProxy.setCurrentView(focusedView);
        }
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public void windowClosed(final DockingWindow window) {
        if (window instanceof View) {
          // Remove definitely from layout
          rootWindow.removeView((View) window);
          UtilWorkspace.workspaceIsModified();
        }
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public void windowAdded(final DockingWindow addedToWindow, final DockingWindow addedWindow) {
        UtilWorkspace.workspaceIsModified();
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public void windowRemoved(final DockingWindow removedFromWindow, final DockingWindow removedWindow) {
        UtilWorkspace.workspaceIsModified();
      }

      /** {@inheritDoc} */
      @Override
      public void windowMinimized(DockingWindow window) {
        UtilWorkspace.workspaceIsModified();
      }

      /** {@inheritDoc} */
      @Override
      public void windowRestored(DockingWindow window) {
        UtilWorkspace.workspaceIsModified();
      }

    };

    rootWindow.addListener(adapter);
  }

  /**
   * Description : initDockingViewMap method <br>
   */
  protected abstract void initDockingViewMap();

  /**
   * Description : defineWorkspaceLayout method <br>
   */
  protected abstract void defineWorkspaceLayout();

  /**
   * Description : buildDockingWindow method <br>
   */
  private void buildDockingWindow() {
    rootDockingWindowProperties = new RootWindowProperties();

    rootWindow = GDockingUtil.createRootWindow(viewsMap, WORKSPACE_SERIALIZER, true);
    rootWindow.getWindowBar(Direction.UP).setEnabled(false);
    rootWindow.getRootWindowProperties().getTabWindowProperties().getTabbedPanelProperties().setTabAreaOrientation(Direction.UP);

    defineDockingTheme();
  }

  /**
   * Description : defineDockingTheme method <br>
   */
  protected void defineDockingTheme() {
    // Set gradient theme. The theme properties object is the super object of our properties object, which
    // means our property value settings will override the theme values
    dockingWindowTheme = new ShapedGradientDockingTheme();
    rootDockingWindowProperties.addSuperObject(dockingWindowTheme.getRootWindowProperties());

    // Apply Title bar style for views
    final RootWindowProperties titleBarStyleProperties = PropertiesUtil.createTitleBarStyleRootWindowProperties();
    rootDockingWindowProperties.addSuperObject(titleBarStyleProperties);

    // Our properties object is the super object of the root window properties object, so all property values of the
    // theme and in our property object will be used by the root window
    rootWindow.getRootWindowProperties().addSuperObject(rootDockingWindowProperties);
  }

  /**
   * @see #rootWindow
   * @return the workspace : See field description
   */
  public GRootWindow getRootWindow() {
    return rootWindow;
  }

  public WorkspaceDto getWorkspaceDto() {
    return workspaceDto;
  }

  public void setConfiguration(final WorkspaceDto configuration) {
    this.workspaceDto = configuration;
  }

}
