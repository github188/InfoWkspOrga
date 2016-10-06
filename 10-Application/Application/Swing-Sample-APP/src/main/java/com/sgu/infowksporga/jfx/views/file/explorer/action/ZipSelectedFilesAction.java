package com.sgu.infowksporga.jfx.views.file.explorer.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.dialog.zip.ZipSelectedFilesDlg;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.rules.IsAtLeastViewFileSelectedRule;

/**
 * Description : Zip files to a specified location Action class<br>
 *
 * @author SGU
 */

public class ZipSelectedFilesAction extends AbstractInfoWrkspOrgaAction {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * The reference to get the directory tree
   */
  private final FileExplorerView fileExplorerView;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "file.explorer.view.action.zip.files.text", value = "Zip les fichiers sélectionnés"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.zip.files.tooltip", value = "Zip les fichiers sélectionnés"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.zip.files.icon", value = "/icons/zip.png"), // Force \n
  })
  public ZipSelectedFilesAction(final FileExplorerView fileExplorerView) {
    super("file.explorer.view.action.zip.files");
    this.fileExplorerView = fileExplorerView;

    setRule(new IsAtLeastViewFileSelectedRule(fileExplorerView));
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    final ZipSelectedFilesDlg dialog = new ZipSelectedFilesDlg(fileExplorerView);

    dialog.pack();
    dialog.centerDialogVsScreen();
    dialog.setVisible(true);

  }
}
