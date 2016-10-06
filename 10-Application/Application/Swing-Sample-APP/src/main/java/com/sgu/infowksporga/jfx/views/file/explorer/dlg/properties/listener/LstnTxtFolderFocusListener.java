package com.sgu.infowksporga.jfx.views.file.explorer.dlg.properties.listener;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.SwingUtilities;

import com.sgu.infowksporga.jfx.views.file.explorer.dlg.properties.FileExplorerConfigurationPanel;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class LstnTxtFolderFocusListener.
 */
@Slf4j
public class LstnTxtFolderFocusListener extends FocusAdapter {

  /** The panel. */
  private FileExplorerConfigurationPanel panel;

  /**
   * The Constructor.
   *
   * @param panel the panel
   */
  public LstnTxtFolderFocusListener(final FileExplorerConfigurationPanel panel) {
    super();
    this.panel = panel;
  }

  /** {@inheritDoc} */
  @Override
  public void focusGained(final FocusEvent e) {
  }

  /** {@inheritDoc} */
  @Override
  public void focusLost(final FocusEvent evt) {
    super.focusLost(evt);

    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        panel.validateFolder();
      }
    });

  }
}