package com.sgu.infowksporga.jfx.app.mvc;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;

import com.sgu.core.framework.gui.jfx.control.DigitalClock;
import com.sgu.core.framework.gui.jfx.control.hdrbtm.GStatusBar;
import com.sgu.core.framework.gui.jfx.control.hdrbtm.GToolBar;
import com.sgu.core.framework.gui.jfx.control.pane.GBorderPane;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.screen.AGView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class ApplicationViewFxml.
 */

/** The Constant log. */
@Slf4j

/**
 * Sets the digital clock.
 *
 * @param digitalClock the digital clock
 */
@Setter

/**
 * Gets the digital clock.
 *
 * @return the digital clock
 */
@Getter
public class ApplicationViewFxml extends AGView<ApplicationScreen, ApplicationModel, ApplicationController> implements Initializable {

  /** The cb show hide item notifications. */
  public static final String CB_SHOW_HIDE_ITEM_NOTIFICATIONS = "Notifications";

  /** The cb show hide item menu. */
  public static final String CB_SHOW_HIDE_ITEM_MENU = "Menu";

  /** The pln menu bar. */
  @FXML
  private MenuBar pnlMenuBar;

  /** The pnl tool bar. */
  @FXML
  private GToolBar pnlToolBar;

  @FXML
  private VBox vboxTop;

  @FXML
  private Button btnExit;

  @FXML
  private Button btnAddWebView;

  @FXML
  private ToggleButton tgBtnPerspectiveVisible;

  @FXML
  private SplitPane spnApplication;

  @FXML
  private SplitPane spnWorkspace;

  @FXML
  private AnchorPane ancPnlPerspective;

  @FXML
  private AnchorPane ancPnlWorkspace;

  @FXML
  private TitledPane ttlPnlNotifications;

  @FXML
  private TabPane tabPnlNotifications;

  @FXML
  private Tab tabValidationResult;

  @FXML
  private ListView lstValidationResult;

  @FXML
  private Tab tabChat;

  @FXML
  private Tab tabMail;

  @FXML
  private Accordion acdPerspective;

  @FXML
  private TitledPane ttlPnlPerspective;

  @FXML
  private GBorderPane brdPnlPerspective;

  @FXML
  private TitledPane ttlPnlWorkspaceMap;

  @FXML
  private AnchorPane ancPnlWorkspaceMapView;

  @FXML
  private BorderPane pnlWorkspace;

  @FXML
  private BorderPane pnlApplication;

  @FXML
  private GStatusBar pnlStatusBar;

  @FXML
  private CheckComboBox<String> cbShowHide;

  /*------------------------------------------------------*/
  // ==> Controls not defined in FXML file
  /*------------------------------------------------------*/

  /** The digital clock. */
  private DigitalClock digitalClock;

  /** The dock pane. */
  private GDockPane dockPane;

  /**
   * The Constructor.
   */
  public ApplicationViewFxml() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createUI() {
    super.createUI();

    // Add digital clock and date in the status bar
    digitalClock = new DigitalClock();
    pnlStatusBar.getRightItems().add(digitalClock);
    pnlStatusBar.setProgress(0);

    /*------------------------------------------------------------------*/
    // Reorder Toolbar button
    /*------------------------------------------------------------------*/
    // Move btnAddWebView to the left of the toolbar
    pnlToolBar.getChildren().remove(btnAddWebView);
    pnlToolBar.addLeftItems(btnAddWebView);

    pnlToolBar.addLeftItems(new Separator(Orientation.VERTICAL));

    // Move tgBtnPerspectiveVisible to the left of the toolbar
    pnlToolBar.getChildren().remove(tgBtnPerspectiveVisible);
    pnlToolBar.addLeftItems(new Separator(Orientation.VERTICAL));
    pnlToolBar.addLeftItems(tgBtnPerspectiveVisible);

    // Add Default Dockable Workspace
    controller().displayNewEmptyWorkspace();

    // Deploy Perspective Accordion panel
    Platform.runLater(() -> {
      ttlPnlPerspective.setExpanded(true);
    });

  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();

    // Define possible values for the Show/Hide combo box
    final ObservableList<String> showHideElements = FXCollections.observableArrayList();
    showHideElements.add(CB_SHOW_HIDE_ITEM_MENU);
    showHideElements.add(CB_SHOW_HIDE_ITEM_NOTIFICATIONS);

    // At Screen init all items are selected
    cbShowHide.getItems().addAll(showHideElements);
    final IndexedCheckModel<String> checkModel = cbShowHide.getCheckModel();
    checkModel.checkAll();

  }

  /** {@inheritDoc} */
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    super.initialize(location, resources);

    spnWorkspace.setDividerPositions(0.20);

  }

}
