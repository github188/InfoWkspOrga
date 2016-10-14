package com.sgu.infowksporga.jfx.view.web.action;

import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.infowksporga.jfx.view.action.AViewConfigurationAction;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class WebViewConfigurationAction.
 */
@Slf4j
public class WebViewConfigurationAction extends AViewConfigurationAction {

  /**
   * The Constructor.
   *
   * @param control The control
   */
  public WebViewConfigurationAction(final Control control) {
    super(control);
  }

  /** {@inheritDoc} */
  @Override
  public void handle(final ActionEvent event) {
    UtilGUIMessage.showNotYetImplementedDlg();
  }

}
