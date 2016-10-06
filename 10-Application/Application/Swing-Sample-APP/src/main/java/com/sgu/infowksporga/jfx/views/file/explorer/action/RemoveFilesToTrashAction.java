package com.sgu.infowksporga.jfx.views.file.explorer.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.rules.IsAtLeastViewFileSelectedRule;
import com.sgu.infowksporga.jfx.zfacade.local.edit.RemoveFilesToTrashServiceUI;

/**
 * Description : Remove Files To Trash Action class<br>
 *
 * @author SGU
 */
public class RemoveFilesToTrashAction extends AbstractInfoWrkspOrgaAction {
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
                @I18nProperty(key = "file.explorer.view.action.remove.files.text", value = "Supprimer les fichiers"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.remove.files.tooltip",
                value = "Déplace les fichiers/dossiers selectionnés (de la vue en cours) dans la poubelle de l'OS"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.remove.files.icon", value = "/icons/trash.png"), // Force \n
  })
  public RemoveFilesToTrashAction(final FileExplorerView fileExplorerView) {
    super("file.explorer.view.action.remove.files");
    this.fileExplorerView = fileExplorerView;

    setRule(new IsAtLeastViewFileSelectedRule(fileExplorerView));
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    final RemoveFilesToTrashServiceUI serviceUI = SpringBeanHelper.getImplementationByInterface(RemoveFilesToTrashServiceUI.class);
    serviceUI.execute(fileExplorerView);
  }
}
