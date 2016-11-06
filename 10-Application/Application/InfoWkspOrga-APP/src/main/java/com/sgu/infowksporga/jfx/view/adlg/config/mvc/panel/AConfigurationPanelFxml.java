package com.sgu.infowksporga.jfx.view.adlg.config.mvc.panel;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.core.framework.gui.jfx.screen.AGScreen;
import com.sgu.core.framework.gui.jfx.screen.AGView;

import javafx.fxml.Initializable;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class AConfigurationPanelFxml.
 */
@Slf4j
@Setter
@Getter

public class AConfigurationPanelFxml extends AGView<AGScreen, AGModel, AGController> implements Initializable {

  //---------------------------------
  //Configuration Panel components
  //---------------------------------

  //------------------------------------------------------
  // ==> Controls not defined in FXML file
  //------------------------------------------------------

  /**
   * The Constructor.
   */
  public AConfigurationPanelFxml() {
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

  }

}
