package com.sgu.infowksporga.jfx.main.ui;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.DigitalClock;
import com.sgu.core.framework.gui.jfx.control.GDockPane;
import com.sgu.core.framework.gui.jfx.control.GStatusBar;
import com.sgu.core.framework.gui.jfx.control.GToolBar;
import com.sgu.core.framework.gui.jfx.screen.AGView;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.jfx.util.UtilControl;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
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
public class ApplicationViewFxml extends AGView<Scene, ApplicationModel, ApplicationController> implements Initializable {

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
  private ToggleButton tgBtnPerspectiveVisible;

  @FXML
  private SplitPane spnApplication;

  @FXML
  private SplitPane spnWorkspace;

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
  private TitledPane ttlPnlWorkspaceMap;

  @FXML
  private AnchorPane pnlPerspective;

  @FXML
  private AnchorPane pnlWorkspaceMapView;

  @FXML
  private BorderPane pnlWorkspace;

  @FXML
  private BorderPane pnlApplication;

  @FXML
  private GStatusBar pnlStatusBar;

  @FXML
  private CheckComboBox<String> cbShowHide;

  /*------------------------------------------------------*/
  // ==> Controls not in FXML file
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

    // Deploy Perspective Accordion panel
    Platform.runLater(() -> {
      ttlPnlPerspective.setExpanded(true);
    });

    // Move tgBtnPerspectiveVisible to the right of the toolbar
    pnlToolBar.getChildren().remove(tgBtnPerspectiveVisible);
    pnlToolBar.addLeftItems(new Separator(Orientation.VERTICAL));
    pnlToolBar.addLeftItems(tgBtnPerspectiveVisible);

    // Add Default Dockable Workspace
    controller().createNewWorkspace();
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

    updateBtnExitFromProperties();

  }

  /**
   * Initialize from properties btn exit.
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "application.action.button.exit" + I18NConstant.TEXT, value = ""), // Force /n
                @I18nProperty(key = "application.action.button.exit" + I18NConstant.TOOLTIP_TEXT, value = "Fermer l'application"), // Force /n 
                @I18nProperty(key = "application.action.button.exit" + I18NConstant.ICON, value = "/icons/exit.png"), // Force /n
                @I18nProperty(key = "application.action.button.exit" + I18NConstant.SHORTCUT, value = "Control+X"), // Force /n
                @I18nProperty(key = "application.action.button.exit" + I18NConstant.NAME, value = "btnExit"), // Force /n
  })
  private void updateBtnExitFromProperties() {
    UtilControl.applyBundleConfigToButton("application.action.button.exit", btnExit);
  }

  /**
   * Exit application action.
   *
   * @param event the event
   */
  @FXML
  public void exitApplicationAction(final ActionEvent event) {
    controller().processExitApplication();
  }

  /**
   * Display or hide application menu.
   *
   * @param event the event
   */
  @FXML
  public void displayOrHideAcdPerspective(final ActionEvent event) {
    if (tgBtnPerspectiveVisible.isSelected() == false) {
      pnlApplication.setLeft(null);
    }
    else {
      pnlApplication.setLeft(acdPerspective);
    }
  }

}
