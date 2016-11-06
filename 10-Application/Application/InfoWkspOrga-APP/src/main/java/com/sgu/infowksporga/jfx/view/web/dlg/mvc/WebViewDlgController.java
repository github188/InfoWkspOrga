package com.sgu.infowksporga.jfx.view.web.dlg.mvc;

import com.sgu.infowksporga.jfx.view.adlg.config.mvc.AViewDlgController;
import com.sgu.infowksporga.jfx.view.web.dlg.mvc.panel.ViewWebConfigPanelFxml;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The class <strong>AAppViewController</strong>.
 */

@Slf4j
@Setter
@Getter
public class WebViewDlgController extends AViewDlgController<WebViewDlgModel, WebViewDlgViewFxml> {

  /**
   * The Constructor.
   */
  public WebViewDlgController() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createListeners() {
    super.createListeners();

  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    super.bindComponentsWithPojo();
  }

  /** {@inheritDoc} */
  @Override
  protected void initConfigurationPanelBindings() {

    final WebViewDlgViewFxml viewDlg = view();
    final ViewWebConfigPanelFxml configPanel = (ViewWebConfigPanelFxml) viewDlg.getPnlConfigurationController();
    final WebViewDlgModel mdl = model();

    configPanel.getTxtUrl().textProperty().bindBidirectional(mdl.getUrlProperty());
  }

}
