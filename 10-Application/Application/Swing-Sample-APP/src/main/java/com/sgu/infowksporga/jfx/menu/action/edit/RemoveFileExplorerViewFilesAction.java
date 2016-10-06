package com.sgu.infowksporga.jfx.menu.action.edit;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.rules.IsAtLeastViewFileSelectedRule;

/**
 * Description : RemoveDirectoryDeskViewFilesAction class<br>
 * Move to trash all workspace selected files from directory view
 * 
 * @author SGU
 */
public class RemoveFileExplorerViewFilesAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.edit.remove.file.explorer.view.files.text", value = "Supprimer"), // Force /n
                @I18nProperty(key = "menu.edit.remove.file.explorer.view.files.name", value = "DELETE_SELECTED_DIRECTORIES_FILES"), // Force /n
                @I18nProperty(key = "menu.edit.remove.file.explorer.view.files.tooltip",
                value = "Supprime les fichiers/dossiers sélectionnés  de toutes les vues (même non visible)"), // Force /n
                @I18nProperty(key = "menu.edit.remove.file.explorer.view.files.mnemonic", value = "p"), // Force /n
                @I18nProperty(key = "menu.edit.remove.file.explorer.view.files.shortcut", value = "control D"), // Force /n
                @I18nProperty(key = "menu.edit.remove.file.explorer.view.files.icon", value = "/icons/delete.png"), // Force /n
  })
  public RemoveFileExplorerViewFilesAction() {
    super("menu.edit.remove.file.explorer.view.files");
    setRule(new IsAtLeastViewFileSelectedRule(null));
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    //final RemoveFilesToTrashServiceUI facade = SpringBeanHelper.getImplementationByInterface(RemoveFilesToTrashServiceUI.class);
    //facade.execute(facade, null);
    UtilGUI.showNotYetImplementedDlg();
  }
}
