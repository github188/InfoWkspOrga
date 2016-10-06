package com.sgu.infowksporga.jfx.dialog.zip.action;

import com.sgu.core.framework.gui.swing.action.AbstractGAction;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.core.framework.util.Properties;
import com.sgu.infowksporga.jfx.dialog.AbstractFileChooserDlg;
import com.sgu.infowksporga.jfx.dialog.AbstractValidateSelectedFileAction;
import com.sgu.infowksporga.jfx.dialog.zip.ZipSelectedFilesDlg;
import com.sgu.infowksporga.jfx.zfacade.local.edit.ZipSelectedFilesServiceUI;

/**
 * Description : ValidateZipFilesAction class<br>
 * 
 * @author SGU
 */
public class ValidateZipFilesAction extends AbstractValidateSelectedFileAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 2568554388620155754L;

  /**
   * Constructor<br>
   * 
   * @param dialog reference
   */
  public ValidateZipFilesAction(final AbstractFileChooserDlg dialog) {
    super(dialog);
    putValue(AbstractGAction.NAME, "zip.selected.files.dlg.action.validate");

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void callService(final String file, final String basePath, final Properties userLocalSettings) {

    // ZIP files
    final ZipSelectedFilesServiceUI serviceUI = (ZipSelectedFilesServiceUI) SpringBeanHelper.getImplementationByInterface(ZipSelectedFilesServiceUI.class);
    serviceUI.setSaveZipPath(file);

    final ZipSelectedFilesDlg zipDialog = (ZipSelectedFilesDlg) this.dialog;

    serviceUI.setKeepDirectoryFileLocation(zipDialog.getChkKeepDirectoryFileLocation().isSelected());
    serviceUI.setZipExcludeFile(zipDialog.getTxtExcludeFile().getText());
    serviceUI.setZipIncludeFile(zipDialog.getTxtIncludeFile().getText());

    // Create the thread to modify Progress bar
    final Thread zipThread = new Thread(new Runnable() {
      @Override
      public void run() {
        serviceUI.execute(serviceUI, zipDialog.getFileExplorerView());
        dialog.setVisible(false);
      }
    });

    serviceUI.setZipThread(zipThread);
    zipDialog.setZipThread(zipThread);

    zipThread.start();

  }

}