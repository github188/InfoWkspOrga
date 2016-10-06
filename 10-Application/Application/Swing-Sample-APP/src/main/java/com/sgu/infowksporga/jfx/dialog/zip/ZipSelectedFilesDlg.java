package com.sgu.infowksporga.jfx.dialog.zip;

import javax.swing.BorderFactory;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GCheckbox;
import com.sgu.core.framework.gui.swing.GLabelField;
import com.sgu.core.framework.gui.swing.filechooser.SimpleFileFilter;
import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.util.Properties;
import com.sgu.infowksporga.jfx.dialog.AbstractFileChooserDlg;
import com.sgu.infowksporga.jfx.dialog.zip.action.CancelAction;
import com.sgu.infowksporga.jfx.dialog.zip.action.ValidateZipFilesAction;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.util.UserPreferencesConstant;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;

/**
 * Description : ZipSelectedFilesDlg class<br>
 */
public class ZipSelectedFilesDlg extends AbstractFileChooserDlg {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -8198672854160646831L;

  /**
   * This give the ref to view if necessary
   */
  private FileExplorerView fileExplorerView;

  /**
   * Reference to the thread which create the zip to be abble to stop it if necessary
   */
  private Thread zipThread;

  /**
   * true if we want to keep directory file location
   */
  private GCheckbox chkKeepDirectoryFileLocation;

  /**
   * Can contain excluded File and Directory
   */
  private GTextField txtExcludeFile;

  /**
   * Can contain included File and Directory
   */
  private GTextField txtIncludeFile;

  /**
   * Constructor<br>
   */
  public ZipSelectedFilesDlg() {
    super();
  }

  /**
   * Constructor<br>
   * 
   * @param fileExplorerView reference
   */
  public ZipSelectedFilesDlg(final FileExplorerView fileExplorerView) {
    super();
    this.fileExplorerView = fileExplorerView;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getDefaultDirectory(final Properties userLocalSettings) {
    return userLocalSettings.getProperty(UserPreferencesConstant.ZIP_EXPORT_DIRECTORY_SETTING);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "zip.selected.files.dlg.fieldset", value = "Création du fichier ZIP"), // Force /n
                @I18nProperty(key = "zip.selected.files.dlg.filechooser.file.filter.zip.description", value = "Fichiers zip (*.zip)"), // Force /n

                @I18nProperty(key = "zip.selected.files.dlg.lbl.exclude.file.text", value = "Exclure les fichiers"), // Force /n
                @I18nProperty(key = "zip.selected.files.dlg.lbl.exclude.file.tooltip",
                value = "Indiquer le chemin et le nom du fichier ZIP"), // Force /n

                @I18nProperty(key = "zip.selected.files.dlg.lbl.include.file.text", value = "Inclure les fichiers"),// Force /n
                @I18nProperty(key = "zip.selected.files.dlg.lbl.include.file.tooltip",
                value = "<HTML>Peux contenir des fichiers, des répertoires sous la forme :<br/>*.class<br/>target<br/>Séparé par les \";\"<br/>Example : *.class;target;suivi*01.* </HTML>"), // Force /n

                @I18nProperty(key = "zip.selected.files.dlg.chk.keep.file.dir.text", value = "Conserver le chemin des fichiers"), // Force /n
                @I18nProperty(key = "zip.selected.files.dlg.chk.keep.file.dir.tooltip",
                value = "Si cette case est cochée, on conserve le chemin de fichier depuis la racine"), // Force /n

  })
  protected void buildDialog() {
    super.buildDialog();
    propertiesPanel.setBorder(BorderFactory.createTitledBorder(I18nHelperApp.getMessage("zip.selected.files.dlg.fieldset")));

    final SimpleFileFilter zipFilter = new SimpleFileFilter(I18nHelperApp.getMessage("zip.selected.files.dlg.filechooser.file.filter.zip.description"),
                                                            ".zip");
    hlpDirectory.setFilter(zipFilter);

    final GLabelField lblExcludeFile = new GLabelField("zip.selected.files.dlg.lbl.exclude.file");
    txtExcludeFile = new GTextField(50);
    propertiesPanel.add(lblExcludeFile, "");
    propertiesPanel.add(txtExcludeFile, "growx, wrap");

    final GLabelField lblIncludeFile = new GLabelField("zip.selected.files.dlg.lbl.include.file");
    txtIncludeFile = new GTextField(50);
    propertiesPanel.add(lblIncludeFile, "");
    propertiesPanel.add(txtIncludeFile, "growx, wrap");

    chkKeepDirectoryFileLocation = new GCheckbox();
    chkKeepDirectoryFileLocation.setBundleConfiguration("zip.selected.files.dlg.chk.keep.file.dir", I18nHelperApp.getI18nHelper());
    propertiesPanel.add(chkKeepDirectoryFileLocation, "wrap, spanx 3, align left");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillData() {
    super.fillData();

    final Properties userLocalSettings = GUISession.getInstance().getUserLocalSettings();

    txtExcludeFile.setText(userLocalSettings.getProperty(UserPreferencesConstant.ZIP_EXCLUDE_FILE));
    txtIncludeFile.setText(userLocalSettings.getProperty(UserPreferencesConstant.ZIP_INCLUDE_FILE));

  }

  /**
   * {@inheritDoc}
   */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "zip.selected.files.dlg.title", value = "Création du fichier ZIP"), // Force /n

  })
  protected String getDialogTitle() {
    return "zip.selected.files.dlg.title";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "zip.selected.files.dlg.lbl.file", value = "Nom et destination du ZIP"), // Force /n

  })
  protected String getLabelTitle() {
    return "zip.selected.files.dlg.lbl.file";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected AbstractInfoWrkspOrgaAction buildCancelAction() {
    return new CancelAction(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected AbstractInfoWrkspOrgaAction buildValidateAction() {
    return new ValidateZipFilesAction(this);
  }

  /**
   * @see #fileExplorerView
   * @return the fileExplorerView : See field description
   */
  public FileExplorerView getFileExplorerView() {
    return fileExplorerView;
  }

  /**
   * @see #zipThread
   * @return the zipThread : See field description
   */
  public Thread getZipThread() {
    return zipThread;
  }

  /**
   * @see #zipThread
   * @param zipThread : See field description
   */
  public void setZipThread(final Thread zipThread) {
    this.zipThread = zipThread;
  }

  /**
   * @see #chkKeepDirectoryFileLocation
   * @return the chkKeepDirectoryFileLocation : See field description
   */
  public GCheckbox getChkKeepDirectoryFileLocation() {
    return chkKeepDirectoryFileLocation;
  }

  /**
   * @see #txtExcludeFile
   * @return the txtExcludeFile : See field description
   */
  public GTextField getTxtExcludeFile() {
    return txtExcludeFile;
  }

  /**
   * @see #txtIncludeFile
   * @return the txtIncludeFile : See field description
   */
  public GTextField getTxtIncludeFile() {
    return txtIncludeFile;
  }

}
