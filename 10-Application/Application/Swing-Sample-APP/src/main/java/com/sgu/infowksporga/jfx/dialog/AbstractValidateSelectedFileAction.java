package com.sgu.infowksporga.jfx.dialog;

import java.awt.event.ActionEvent;
import java.io.File;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.util.Properties;
import com.sgu.core.framework.util.UtilIO;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;

/**
 * Description : AbstractValidateSelectedFileAction class<br>
 * 
 * @author SGU
 */
public abstract class AbstractValidateSelectedFileAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 2568554388620155754L;

  /**
   * The reference to get the dialog box
   */
  protected final AbstractFileChooserDlg dialog;

  /**
   * Constructor<br>
   * 
   * @param dialog reference
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "import.data.dlg.action.validate.text", value = "Valider"), // Force /n
  })
  public AbstractValidateSelectedFileAction(final AbstractFileChooserDlg dialog) {
    super("import.data.dlg.action.validate");
    this.dialog = dialog;
  }

  /**
   * Description : validateFields method <br>
   * 
   * @return true if error is detected false otherwise
   */
  private boolean validateFields() {
    UtilGUI.markAllFieldsAsValid(dialog.getContentPane());

    boolean hasError = false;

    final String filePath = dialog.getHlpDirectory().getTextField().getText();

    // Check all field are filled
    if (UtilString.isBlank(filePath)) {
      dialog.getHlpDirectory().getTextField()
            .showErrorMessage(UtilGUI.getMandatoryMessageForField(dialog.getLblFile().getBundleConfigKey()));
      hasError = true;
    }

    return hasError;
  }

  /**
   * Description : TODO Give the description of method <br>
   * 
   * @param e TODO
   */
  @Override
  public void actionPerformed(final ActionEvent e) {

    final boolean hasError = validateFields();

    if (hasError) {

      return;
    }

    final String file = dialog.getHlpDirectory().getCompletePathWithFile();

    final String basePath = UtilIO.getBasePath(file);

    dialog.getHlpDirectory().setBaseDirectory(new File(basePath));

    final Properties userLocalSettings = GUISession.getInstance().getUserLocalSettings();

    callService(file, basePath, userLocalSettings);
  }

  /**
   * Description : callService method <br>
   * 
   * @param file the file path with name
   * @param basePath the location of the file (path without filename)
   * @param userLocalSettings the user settings to update with last selected directory
   */
  protected abstract void callService(final String file, final String basePath, final Properties userLocalSettings);
}
