package com.sgu.infowksporga.jfx.view.web.ui;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewController;
import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewFxml;
import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class WebViewFxml.
 */
@Slf4j
@Setter
@Getter
public class WebViewFxml extends ADockableViewFxml<Scene, ADockableViewModel, ADockableViewController> implements Initializable {

  /** The web view. */
  @FXML
  private WebView webView;

  /*------------------------------------------------------*/
  // ==> Controls not in FXML file
  /*------------------------------------------------------*/
  private WebEngine webEngine;

  /**
   * The Constructor.
   */
  public WebViewFxml() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createUI() {
    super.createUI();
  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();
  }

  /** {@inheritDoc} */
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    super.initialize(location, resources);
    webEngine = webView.getEngine();

  }

}
