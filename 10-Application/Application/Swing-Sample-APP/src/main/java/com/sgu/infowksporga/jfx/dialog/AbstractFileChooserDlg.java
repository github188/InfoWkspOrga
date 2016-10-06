package com.sgu.infowksporga.jfx.dialog;

import java.awt.BorderLayout;
import java.io.File;

import com.sgu.core.framework.gui.swing.GButton;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.GLabelField;
import com.sgu.core.framework.gui.swing.dialog.GDialog;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.textfield.helper.directoryfile.GDirectoryFileFieldHelper;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.util.Properties;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;

import net.miginfocom.swing.MigLayout;

/**
 * Description : AbstractFileChooserDlg class<br>
 */
public abstract class AbstractFileChooserDlg extends GDialog {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -8198672854160646831L;

  /**
   * Ref to properties panel
   */
  protected GPanel propertiesPanel;

  /**
   * Ref to the label file
   */
  protected GLabel lblFile;

  /**
   * Reference to the directory
   */
  protected GDirectoryFileFieldHelper hlpDirectory;

  /**
   * Constructor<br>
   */
  public AbstractFileChooserDlg() {
    super("", true);
    setTitle(getDialogTitle());
    buildDialog();
    fillData();
  }

  /**
   * Description : getDialogTitle method <br>
   * 
   * @return the title of the dialog
   */
  protected abstract String getDialogTitle();

  /**
   * Description : buildDialog method <br>
   */
  protected void buildDialog() {
    final GPanel dialogPanel = new GPanel(new MigLayout("", "[grow,fill]"));

    // Build Actions
    final AbstractInfoWrkspOrgaAction validateAction = buildValidateAction();
    final AbstractInfoWrkspOrgaAction cancelAction = buildCancelAction();

    // Build properties Panel
    propertiesPanel = new GPanel(new MigLayout("insets 2 2 5 2, fill", "[right][grow]"));

    lblFile = new GLabelField();
    lblFile.setBundleConfiguration(getLabelTitle(), I18nHelperApp.getI18nHelper());

    hlpDirectory = new GDirectoryFileFieldHelper();
    hlpDirectory.getTextField().setColumns(30);

    // Build the button panel
    final GPanel buttonPanel = new GPanel(new MigLayout("insets 0 0 0 0, fill", "[right][left]"));
    final GButton validateBt = validateAction.createButton(true);
    final GButton cancelBt = cancelAction.createButton(true);

    buttonPanel.add(validateBt);
    buttonPanel.add(cancelBt);

    // Assemble properties panel
    propertiesPanel.add(lblFile);
    propertiesPanel.add(hlpDirectory, "wrap, growx");

    dialogPanel.add(propertiesPanel, "wrap");
    dialogPanel.add(buttonPanel, "");

    add(dialogPanel, BorderLayout.CENTER);

  }

  /**
   * Description : getLabelTitle method <br>
   * 
   * @return the label title indicate the type of file
   */
  protected abstract String getLabelTitle();

  /**
   * Description : buildCancelAction method <br>
   * 
   * @return the cancel action to use
   */
  protected abstract AbstractInfoWrkspOrgaAction buildCancelAction();

  /**
   * Description : buildValidateAction method <br>
   * 
   * @return the validate action to use
   */
  protected abstract AbstractInfoWrkspOrgaAction buildValidateAction();

  /**
   * Description : fillData method <br>
   */
  protected void fillData() {
    final Properties userLocalSettings = GUISession.getInstance().getUserLocalSettings();
    final String directory = getDefaultDirectory(userLocalSettings);

    // Define base directory of the file chooser
    if (directory != null) {
      hlpDirectory.setBaseDirectory(new File(directory));
    }

  }

  /**
   * Description : getDefaultDirectory method <br>
   * 
   * @param userLocalSettings the settings stored on user preference directory
   * @return the last selected directory
   */
  protected abstract String getDefaultDirectory(Properties userLocalSettings);

  /**
   * @see #lblFile
   * @return the lblFile : See field description
   */
  public GLabel getLblFile() {
    return lblFile;
  }

  /**
   * @see #hlpDirectory
   * @return the hlpDirectory : See field description
   */
  public GDirectoryFileFieldHelper getHlpDirectory() {
    return hlpDirectory;
  }

}
