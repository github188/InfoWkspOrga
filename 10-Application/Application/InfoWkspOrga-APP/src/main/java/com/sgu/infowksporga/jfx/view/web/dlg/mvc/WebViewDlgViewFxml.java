package com.sgu.infowksporga.jfx.view.web.dlg.mvc;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.infowksporga.jfx.view.adlg.config.mvc.AViewDlgViewFxml;
import com.sgu.infowksporga.jfx.view.adlg.config.mvc.panel.AConfigurationPanelFxml;
import com.sgu.infowksporga.jfx.view.web.dlg.mvc.panel.ViewWebConfigPanelFxml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class AAppViewFxml.
 */
@Slf4j
@Setter
@Getter
public class WebViewDlgViewFxml extends AViewDlgViewFxml<WebViewDlgScreen, WebViewDlgModel, WebViewDlgController> implements Initializable {

  /** The pnl configuration controller. */
  @FXML
  private ViewWebConfigPanelFxml pnlConfigurationController;

  /**
   * The Constructor.
   */
  public WebViewDlgViewFxml() {
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

  /** {@inheritDoc} */
  @Override
  public AConfigurationPanelFxml getPnlConfigurationController() {
    return pnlConfigurationController;
  }

  /** {@inheritDoc} */
  @Override
  public void setPnlConfigurationController(final AConfigurationPanelFxml pnlConfigurationController) {
    this.pnlConfigurationController = (ViewWebConfigPanelFxml) pnlConfigurationController;

  }

}
