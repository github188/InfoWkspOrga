package com.sgu.infowksporga.jfx.view.web.action;

import com.sgu.core.framework.gui.jfx.screen.IDisplayMode;
import com.sgu.infowksporga.jfx.view.action.AViewConfigurationAction;
import com.sgu.infowksporga.jfx.view.ui.AAppViewModel;
import com.sgu.infowksporga.jfx.view.web.dlg.mvc.WebViewDlgScreen;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class WebViewConfigurationAction.
 */
@Slf4j
public class WebViewConfigurationAction extends AViewConfigurationAction {

  /** The view model. */
  private AAppViewModel viewModel;

  /**
   * The Constructor.
   *
   * @param control The control
   */
  public WebViewConfigurationAction(final Control control, final AAppViewModel viewModel) {
    super(control);
    this.viewModel = viewModel;
  }

  /** {@inheritDoc} */
  @Override
  public void handle(final ActionEvent event) {

    final WebViewDlgScreen screen = new WebViewDlgScreen();
    screen.showDialog(IDisplayMode.MODE_UPDATE, viewModel);
  }

}
