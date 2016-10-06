package com.sgu.infowksporga.jfx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentEvent;

import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import com.jidesoft.swing.JideSplitPaneDivider;
import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.GFrame;
import com.sgu.core.framework.gui.swing.action.ActionManager;
import com.sgu.core.framework.gui.swing.docking.GRootWindow;
import com.sgu.core.framework.gui.swing.listener.ComponentListenerAdapter;
import com.sgu.core.framework.gui.swing.menu.GMenuBar;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.rule.ComponentRuleManager;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.gui.swing.util.GlobalGUI;
import com.sgu.core.framework.spring.loader.SpringBeanFactory;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.component.MeSplitpane;
import com.sgu.infowksporga.jfx.component.StatusBar;
import com.sgu.infowksporga.jfx.connexion.ConnexionDlg;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.menu.action.edit.CopyFileExplorerViewFilesToClipboardAction;
import com.sgu.infowksporga.jfx.menu.action.edit.CutFileExplorerViewFilesToClipboardAction;
import com.sgu.infowksporga.jfx.menu.action.edit.PasteFileExplorerViewFilesAction;
import com.sgu.infowksporga.jfx.menu.action.edit.RemoveFileExplorerViewFilesAction;
import com.sgu.infowksporga.jfx.menu.action.edit.UnselectNodesForAllFileExplorerViewFilesAction;
import com.sgu.infowksporga.jfx.menu.action.file.ExitAction;
import com.sgu.infowksporga.jfx.menu.action.file.SendFileExplorerViewFilesByMailAction;
import com.sgu.infowksporga.jfx.menu.action.file.ZipFileExplorerViewFilesAction;
import com.sgu.infowksporga.jfx.menu.action.help.AboutAction;
import com.sgu.infowksporga.jfx.menu.action.help.ShowWorkspaceLayoutAction;
import com.sgu.infowksporga.jfx.menu.action.view.AddFileExplorerViewAction;
import com.sgu.infowksporga.jfx.menu.action.view.AddHtmlViewAction;
import com.sgu.infowksporga.jfx.menu.action.view.AddWebViewAction;
import com.sgu.infowksporga.jfx.menu.action.windows.PreferencesAction;
import com.sgu.infowksporga.jfx.menu.action.workspace.CreateWorkspaceAction;
import com.sgu.infowksporga.jfx.menu.action.workspace.EditWorkspaceAction;
import com.sgu.infowksporga.jfx.menu.action.workspace.SaveWorkspaceAction;
import com.sgu.infowksporga.jfx.perspective.AbstractPerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.PerspectiveView;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.util.UtilWorkspace;
import com.sgu.infowksporga.jfx.views.dynamic.workspacemap.WorkspaceMapView;
import com.sgu.infowksporga.jfx.workspace.AbstractWorkspace;
import com.sgu.infowksporga.jfx.workspace.WorkspaceDefault;
import com.sgu.infowksporga.jfx.zfacade.remote._init.ApplicationInitializerFacade;
import com.sgu.infowksporga.jfx.zfacade.remote._init.LoadPreferencesStructureFacade;

import net.infonode.util.Direction;
import net.miginfocom.swing.MigLayout;

/**
 * Description : info wrksp orga Application Frame <br>
 */

@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = "application.name", value = "InfoWrkspOrga"), // Force /n 
              @I18nProperty(key = "application.version", value = "2.0.0"), })
public class InfoWrkspOrgaFrame extends GFrame {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 2563397599839998185L;

  /**
   * The attribute actionManager. This object manages all actions of menu and toolbar.
   */
  private ActionManager actionManager;

  /**
   * Menu bar application Frame
   */
  private GMenuBar menuBar;

  /**
   * Toolbar applicationFrame
   */
  private GPanel toolBar;

  /**
   * Store the Perspective and the Workspace
   */
  private MeSplitpane splitPane;

  /** The pnl perspective. */
  private AbstractPerspectivePanel pnlPerspective;

  /** The wrksp perspective. */
  private WorkspaceDefault wrkspPerspective;

  /** The wrksp selected from the perspective. */
  private WorkspaceDefault wrkspSelected;

  /** The workspace map view. */
  private WorkspaceMapView workspaceMapView;

  /**
   * Status bar
   */
  private StatusBar statusBar;

  /**
   * Constructor<br>
   */
  public InfoWrkspOrgaFrame() {
    super();
  }

  /**
   * Description : main method <br>
   *
   * @param args Not used
   * @throws Exception Launching Error
   */
  public static void main(final String[] args) throws Exception {
    // Schedule a job for the event-dispatching thread:
    // creating and showing this application's GUI.
    // Docking windows should be run in the Swing thread
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new InfoWrkspOrgaFrame();
      }
    });
  }

  /** {@inheritDoc} */
  @Override
  public void initUI() {
    super.initUI();

    GUISession.getInstance().setApplicationFrame(this);
    setSize(1040, 768);

    centerFrameVsScreen();

    // Initialisation du contexte Spring de l'application
    SpringBeanFactory.getInstance().initialize("/spring/application-context.xml");

    // define application icon
    UtilInfoWrkspOrga.setInfoWrkspOrgaAppIcon(this);

    /*
     * Load all external configuration (prez server)
     */
    final ApplicationInitializerFacade facade_1 = SpringBeanHelper.getImplementationByInterface(ApplicationInitializerFacade.class);
    GUISession.getInstance().getServiceDelegate().execute(facade_1, null);

    // TODO Should be called at biz server startup not in Prez client Because it's a DB initialization
    //final LoadPerspectivesStructureFacade facade_2 = SpringBeanHelper.getImplementationByInterface(LoadPerspectivesStructureFacade.class);
    //GUISession.getInstance().getServiceDelegate().execute(facade_2, null);

    // TODO Should be called at biz server startup not in Prez client Because it's a DB initialization
    final LoadPreferencesStructureFacade facade_3 = SpringBeanHelper.getImplementationByInterface(LoadPreferencesStructureFacade.class);
    GUISession.getInstance().getServiceDelegate().execute(facade_3, null);

    showConnexionDialog();

  }

  /** {@inheritDoc} */
  @Override
  public void createUI() {
    super.createUI();
    buildFrame();
  }

  /**
   * Description : buildFrame method <br>
   */
  private void buildFrame() {

    // TODO show splash screen during application loading
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        buildActionManager();

        buildMenuBar();
        buildToolbar();

        /* Refresh the frame menu and tool bar */
        buildFrameMenuAction();
        buildFrameToolBarAction();

        buildStatusBar();

        buildApplicationPanel();

        // Display application frame
        setVisible(true);

        SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
            ComponentRuleManager.executeRuleOnAllComponent(GUISessionProxy.getInfoWrkspOrgaFrame());
          }
        });
      }
    });
  }

  @Override
  public void fillUI() {
    // TODO Auto-generated method stub
    super.fillUI();
  }

  /**
   * Description : build Perspective Action Manager method <br>
   */
  protected void buildActionManager() {
    // Define the Menu and Toolbar actions
    actionManager = new ActionManager();

    actionManager.addAction(new ExitAction());

    actionManager.addAction(new ZipFileExplorerViewFilesAction());
    actionManager.addAction(new SendFileExplorerViewFilesByMailAction());

    actionManager.addAction(new UnselectNodesForAllFileExplorerViewFilesAction());
    actionManager.addAction(new CutFileExplorerViewFilesToClipboardAction());
    actionManager.addAction(new CopyFileExplorerViewFilesToClipboardAction());
    actionManager.addAction(new PasteFileExplorerViewFilesAction());
    actionManager.addAction(new RemoveFileExplorerViewFilesAction());

    actionManager.addAction(new CreateWorkspaceAction());
    actionManager.addAction(new EditWorkspaceAction());
    actionManager.addAction(new SaveWorkspaceAction());

    actionManager.addAction(new AddHtmlViewAction());
    actionManager.addAction(new AddFileExplorerViewAction());
    actionManager.addAction(new AddWebViewAction());

    actionManager.addAction(new PreferencesAction());

    actionManager.addAction(new ShowWorkspaceLayoutAction());
    actionManager.addAction(new AboutAction());

    /*
     * actionManager.addAction(new PerspectiveCreateAction());
     * actionManager.addAction(new PerspectiveCopyAction());
     * actionManager.addAction(new PerspectiveUpdatePropertiesAction());
     * actionManager.addAction(new PerspectiveRemoveAction());
     * actionManager.addAction(new WorkspaceCreateAction());
     * actionManager.addAction(new WorkspaceCopyAction());
     * actionManager.addAction(new WorkspaceUpdatePropertiesAction());
     * actionManager.addAction(new WorkspaceDefineTabTitleAction());
     * actionManager.addAction(new WorkspaceRemoveAction());
     * actionManager.addAction(new AddWorkspaceMapViewAction());
     * actionManager.addAction(new AddFileExplorerViewAction());
     * actionManager.addAction(new AddQuickActionViewAction());
     * actionManager.addAction(new AddLinkViewAction());
     * actionManager.addAction(new AddProjectConfigurationViewAction());
     * actionManager.addAction(new PreferencesDisplayAction());
     * actionManager.addAction(new WorkspaceDisplayLayoutAction());
     * actionManager.addAction(new ManageUserAction());
     * actionManager.addAction(new ImportUserAction());
     * actionManager.addAction(new ImportUserProjectsAffectationAction());
     * actionManager.addAction(new CreateProjectAction());
     * actionManager.addAction(new UpdateProjectAction());
     * actionManager.addAction(new RemoveProjectAction());
     * actionManager.addAction(new ImportProjectAction());
     * actionManager.addAction(new SendFileByMailAction());
     */
  }

  /**
   * Description : build Frame MenuBar method <br>
   */
  protected void buildFrameMenuAction() {
    MenuManager.refreshApplicationMenu(actionManager);
  }

  /**
   * Description : build Frame ToolBar method <br>
   */
  private void buildFrameToolBarAction() {
    MenuManager.refreshApplicationToolbar(actionManager);
  }

  /**
   * Description : buildStatusBar method <br>
   */
  private void buildStatusBar() {
    statusBar = new StatusBar();
  }

  /**
   * Description : buildMenuBar method <br>
   * Builds menuBar.
   */
  private void buildMenuBar() {
    menuBar = new GMenuBar();
    this.setJMenuBar(menuBar);
  }

  /**
   * Description : buildToolbar method <br>
   */
  private void buildToolbar() {
    toolBar = new GPanel(new MigLayout("insets 2 5 2 5"));
    toolBar.setBackground(GlobalGUI.GREY_BACKGROUND_COLOR);

    toolBar.setBorder(new MatteBorder(0, 0, 1, 0, new Color(182, 188, 204)));
    add(toolBar, BorderLayout.NORTH);
  }

  /**
   * Description : buildApplicationPanel method <br>
   * By default display the Cmmi perspective
   */
  private void buildApplicationPanel() {
    splitPane = new MeSplitpane();
    splitPane.setOneTouchExpandable(true);
    splitPane.setShowGripper(true);
    splitPane.setPrefereredDividerlocation(240);
    splitPane.setDividerLocations(new int[] { splitPane.getPrefereredDividerlocation() });

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        if (splitPane.getComponentCount() > 1 && splitPane.getComponent(1) != null) {
          final JideSplitPaneDivider divider = (JideSplitPaneDivider) splitPane.getComponent(1);
          UtilInfoWrkspOrga.setDividerColor(divider);
        }
      }
    });

    getContentPane().add(splitPane, BorderLayout.CENTER);

    getContentPane().add(statusBar, BorderLayout.SOUTH);

    // Define the default perspective to display
    buildPerspectivePanel();
    showPerspectiveWorkspace();
  }

  /**
   * Description : buildPerspectivePanel method <br>
   * Use a property to build the perspective
   *
   * @return The perspective panel to use
   */
  protected AbstractPerspectivePanel buildPerspectivePanel() {

    final String className = I18nHelperApp.getMessage("perspective.class");
    pnlPerspective = null;

    try {
      pnlPerspective = (AbstractPerspectivePanel) Class.forName(className).newInstance();
    } catch (final Exception e) {
      throw new TechnicalException(e);
    }

    return pnlPerspective;
  }

  /** {@inheritDoc} */
  @Override
  public void createListeners() {
    super.createListeners();

    this.addComponentListener(new ComponentListenerAdapter() {
      @Override
      public void componentResized(final ComponentEvent e) {
        splitPane.setDividerLocations(new int[] { splitPane.getPrefereredDividerlocation() });
      }
    });
  }

  /**
   * Gets the pnl perspective.
   *
   * @return the pnl perspective
   */
  public AbstractPerspectivePanel getPnlPerspective() {
    return pnlPerspective;
  }

  /**
   * Description : show the given Perspective <br>
   *
   * @param perspective The perspective to display
   */
  public void showPerspectiveWorkspace() {

    wrkspPerspective = new WorkspaceDefault() {
      @Override
      protected void defineWorkspaceLayout() {
        // do nothing for the perspective workspace
      }
    };

    // Create Perspective view
    final PerspectiveView vwPerspective = new PerspectiveView((PerspectivePanel) pnlPerspective);
    vwPerspective.getWindowProperties().setCloseEnabled(false);

    final GRootWindow rootWindow = wrkspPerspective.getRootWindow();
    rootWindow.setWindow(vwPerspective);

    // Create Workspace Map view
    workspaceMapView = new WorkspaceMapView();
    UtilWorkspace.addNewViewToWorkspace(wrkspPerspective, workspaceMapView, false);
    workspaceMapView.getWindowProperties().setCloseEnabled(false);

    // enable the minimize button for views and tab
    rootWindow.getWindowBar(Direction.DOWN).setEnabled(true);

    // Display workspace Perspective
    splitPane.removeAll();
    splitPane.add(wrkspPerspective.getRootWindow());

  }

  /**
   * Description :show the given Workspace <br>
   *
   * @param workspace The workspace to display
   */
  public void showSelectedWorkspace(final AbstractWorkspace workspace) {

    wrkspSelected = (WorkspaceDefault) workspace;

    if (splitPane.getComponentCount() > 1) {

      for (int i = 1; i < splitPane.getComponentCount(); i++) {
        splitPane.remove(i);
        i--;
      }
    }

    final GRootWindow rootWindow = wrkspSelected.getRootWindow();
    // enable the minimize button for views and tab
    rootWindow.getWindowBar(Direction.DOWN).setEnabled(true);

    splitPane.add(rootWindow);

    GUISessionProxy.setCurrentWorkspaceHasChanged(false);
    GUISessionProxy.setCurrentView(null);

    // Force the divider location to the last position selected by user
    splitPane.setDividerLocations(new int[] { splitPane.getPrefereredDividerlocation() });

    // Refresh the split pane
    splitPane.revalidate();
    splitPane.repaint();

    // Refresh Workspace Map
    GUISessionProxy.getInfoWrkspOrgaFrame().getWorkspaceMapView().refreshWorkspaceMapHTML();

    ComponentRuleManager.executeRuleOnAllComponent(GUISessionProxy.getInfoWrkspOrgaFrame());
  }

  /**
   * Initializes the frame and shows it.
   */
  private void showConnexionDialog() {

    final ConnexionDlg dialog = new ConnexionDlg();
    dialog.showDialog();
  }

  /**
   * DOCUMENT ME!
   *
   * @return the actionManager : See field description
   * @see #actionManager
   */
  public ActionManager getActionManager() {

    return actionManager;
  }

  /**
   * DOCUMENT ME!
   *
   * @return the toolBar : See field description
   * @see #toolBar
   */
  public GPanel getToolBar() {

    return toolBar;
  }

  /**
   * DOCUMENT ME!
   *
   * @return the statusBar : See field description
   * @see #statusBar
   */
  public StatusBar getStatusBar() {

    return statusBar;
  }

  /**
   * DOCUMENT ME!
   *
   * @return the splitPane : See field description
   * @see #splitPane
   */
  public MeSplitpane getSplitPane() {

    return splitPane;
  }

  /**
   * @return the menuBar
   */
  public final GMenuBar getGMenuBar() {
    return menuBar;
  }

  /**
   * @return the wrkspPerspective
   */
  public final AbstractWorkspace getWrkspPerspective() {
    return wrkspPerspective;
  }

  /**
   * @return the wrkspSelected
   */
  public final AbstractWorkspace getWrkspSelected() {
    return wrkspSelected;
  }

  /**
   * @return the workspaceMapView
   */
  public final WorkspaceMapView getWorkspaceMapView() {
    return workspaceMapView;
  }

  /**
   * @param wrkspSelected the wrkspSelected to set
   */
  public final void setWrkspSelected(final WorkspaceDefault wrkspSelected) {
    this.wrkspSelected = wrkspSelected;
  }

}
