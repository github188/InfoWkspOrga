package com.sgu.infowksporga.jfx.main.ui;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.StatusBar;

import com.sgu.core.framework.gui.jfx.control.DigitalClock;
import com.sgu.core.framework.gui.jfx.control.GToolBar;
import com.sgu.core.framework.gui.jfx.screen.AbstractView;
import com.sgu.core.framework.gui.jfx.util.UtilGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class ApplicationViewFxml.
 */
@Slf4j
@Setter
@Getter
public class ApplicationViewFxml extends AbstractView<Scene, ApplicationModel, ApplicationController> implements Initializable {

  /** The pnl status bar. */
  @FXML
  private StatusBar pnlStatusBar;

  /** The pnl status bar. */
  @FXML
  private GToolBar pnlToolBar;

  /** The acn perspectives. */
  @FXML
  private TitledPane acnPerspectives;
  //private ??? pnlPerspectives;

  /** The acn workspace map. */
  @FXML
  private TitledPane acnWorkspaceMap;
  //private ??? pnlWorkspaceMap;

  @FXML
  private Button btnExit;

  /** The digital clock. */
  private DigitalClock digitalClock;

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

    // Expand perspective tree node at startup
    acnPerspectives.setExpanded(true);

    // Add digital clock and date in the status bar
    digitalClock = new DigitalClock();
    pnlStatusBar.getRightItems().add(digitalClock);
    pnlStatusBar.setProgress(0);
  }

  /** {@inheritDoc} */
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    super.initialize(location, resources);

    // I18N Label

  }

  @FXML
  public void exitApplicationAction(final ActionEvent event) {
    final Object source = event.getSource();
    UtilGUI.showNotYetImplementedDlg();
  }

}
