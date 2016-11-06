package com.sgu.infowksporga.jfx.view.web.ui;

import com.sgu.infowksporga.jfx.view.ui.AAppViewController;
import com.sgu.infowksporga.jfx.view.web.action.WebViewConfigurationAction;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The class <strong>WebViewController</strong>.
 */

@Slf4j
@Setter
@Getter
public class WebViewController extends AAppViewController<WebViewModel, WebViewFxml> {

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

    // init Action to display the view configuration Dialog
    new WebViewConfigurationAction(view().getBtnWorkspaceViewConfig(), model());
    view().getBtnWorkspaceViewConfig().setText(""); // Don't want to display text for this button

    // Add the event specific to this view to capture the last selected View (DockNode)
    view().getWebView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(final MouseEvent mEvent) {
        manageChangeDockNodeViewFocus();
      }
    });

  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    super.bindComponentsWithPojo();

    view().getWebEngine().load(model().getUrl());
  }

}
