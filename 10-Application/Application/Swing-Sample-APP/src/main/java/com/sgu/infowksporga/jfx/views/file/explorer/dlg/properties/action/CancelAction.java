package com.sgu.infowksporga.jfx.views.file.explorer.dlg.properties.action;

import java.awt.event.ActionEvent;

import com.sgu.infowksporga.jfx.views.common.dialog.action.AbstractViewCancelAction;
import com.sgu.infowksporga.jfx.views.file.explorer.dlg.properties.FileExplorerViewPropertiesDlg;

/**
 * Description : CancelAction class<br>
 * 
 * @author SGU
 */
public class CancelAction extends AbstractViewCancelAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 2568554388620155754L;

  /**
   * The reference to get the dialog box
   */
  private final FileExplorerViewPropertiesDlg dialog;

  /**
   * Constructor<br>
   */
  public CancelAction(final FileExplorerViewPropertiesDlg dialog) {
    super(dialog);
    this.dialog = dialog;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void actionPerformed(final ActionEvent e) {
    dialog.setCancelClicked(true);
    dialog.setVisible(false);
  }
}