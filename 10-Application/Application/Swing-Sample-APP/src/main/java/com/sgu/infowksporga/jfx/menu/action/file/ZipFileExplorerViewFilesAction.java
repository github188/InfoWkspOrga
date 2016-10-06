package com.sgu.infowksporga.jfx.menu.action.file;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.dialog.zip.ZipSelectedFilesDlg;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.rules.IsAtLeastViewFileSelectedRule;

/**
 * Description : ZipSelectedFilesAction class<br>
 * 
 * @author SGU
 */
public class ZipFileExplorerViewFilesAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.file.zip.text", value = "Zip files"), // Force /n
                @I18nProperty(key = "menu.file.zip.name", value = "ZIP_SELECTED_FILES"), // Force /n
                @I18nProperty(key = "menu.file.zip.tooltip",
                value = "Zip les fichiers/dossiers sélectionnés toutes vues (même non visible)"), // Force /n
                @I18nProperty(key = "menu.file.zip.mnemonic", value = "Z"), // Force /n
                @I18nProperty(key = "menu.file.zip.shortcut", value = "control Z"), // Force /n
                @I18nProperty(key = "menu.file.zip.icon", value = "/icons/zip.png"), // Force /n
  })
  public ZipFileExplorerViewFilesAction() {
    super("menu.file.zip");
    setRule(new IsAtLeastViewFileSelectedRule(null));
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    final ZipSelectedFilesDlg dlg = new ZipSelectedFilesDlg();

    dlg.pack();
    dlg.centerDialogVsScreen();
    dlg.setVisible(true);

  }
}
