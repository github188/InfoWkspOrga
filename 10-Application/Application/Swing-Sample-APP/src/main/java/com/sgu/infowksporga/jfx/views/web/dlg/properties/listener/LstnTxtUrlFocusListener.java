package com.sgu.infowksporga.jfx.views.web.dlg.properties.listener;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.SwingUtilities;

import com.sgu.infowksporga.jfx.views.web.dlg.properties.WebViewConfigurationPanel;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class LstnTxtUrlFocusLost.
 */
@Slf4j
public class LstnTxtUrlFocusListener extends FocusAdapter {

  /** The panel. */
  private WebViewConfigurationPanel panel;

  /**
   * The Constructor.
   *
   * @param panel the panel
   */
  public LstnTxtUrlFocusListener(WebViewConfigurationPanel panel) {
    super();
    this.panel = panel;
  }

  /** {@inheritDoc} */
  @Override
  public void focusGained(FocusEvent e) {
  }

  /** {@inheritDoc} */
  @Override
  public void focusLost(FocusEvent evt) {
    super.focusLost(evt);

    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        panel.validateURL();
      }
    });

  }
}