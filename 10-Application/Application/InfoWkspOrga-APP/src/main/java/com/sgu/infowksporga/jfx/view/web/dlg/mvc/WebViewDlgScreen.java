package com.sgu.infowksporga.jfx.view.web.dlg.mvc;

import com.sgu.infowksporga.jfx.view.adlg.config.mvc.AViewDlgScreen;
import com.sgu.infowksporga.jfx.view.adlg.config.mvc.AViewDlgViewFxml;

/**
 * The Class WebViewScreen.
 *
 * @param <WebViewDlgModel> the generic type
 * @param <WebViewDlgViewFxml> the generic type
 * @param <WebViewDlgController> the generic type
 */
public class WebViewDlgScreen extends AViewDlgScreen<WebViewDlgModel, WebViewDlgViewFxml, WebViewDlgController> {

  /**
   * The Constructor.
   */
  public WebViewDlgScreen() {
  }

  /**
   * Inits the mvc.
   * Initialized all components by default
   */

  @Override
  public void initMVC() {
    super.initMVC();
  }

  /** {@inheritDoc} */
  @Override
  public void finalizeMvcBuildOfComponent() {
    super.finalizeMvcBuildOfComponent();

    final AViewDlgViewFxml view = (AViewDlgViewFxml) view();

    // Affect the controller to the included views
    view.getPnlConfigurationController().setController(controller());

    // Affect the model to the included views
    view.getPnlConfigurationController().setModel(model());

    // Build the UI of the included views
    view.getPnlConfigurationController().buildUI();
  }
}
