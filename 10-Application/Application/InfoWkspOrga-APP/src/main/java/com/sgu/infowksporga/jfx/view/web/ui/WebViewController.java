package com.sgu.infowksporga.jfx.view.web.ui;

import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewController;
import com.sgu.infowksporga.jfx.view.web.action.WebViewConfigurationAction;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The class <strong>WebViewController</strong>.
 */

@Slf4j
@Setter
@Getter
public class WebViewController extends ADockableViewController<WebViewModel, WebViewFxml> {

  /**
   * The Constructor.
   */
  public WebViewController() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createListeners() {
    super.createListeners();

    new WebViewConfigurationAction(view().getBtnWorkspaceViewConfig());
    view().getBtnWorkspaceViewConfig().setText("");

  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    super.bindComponentsWithPojo();
  }

}
