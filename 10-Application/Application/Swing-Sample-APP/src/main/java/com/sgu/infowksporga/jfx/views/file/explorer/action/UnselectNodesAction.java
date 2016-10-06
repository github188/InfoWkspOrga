package com.sgu.infowksporga.jfx.views.file.explorer.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.rules.IsAtLeastViewFileSelectedRule;
import com.sgu.infowksporga.jfx.zfacade.local.edit.UnselectAllNodesForAllFileViewServiceUI;

/**
 * Description : Unselect Nodes For Directory Desk View Action class<br>
 * Unselect all tree item from directory Desk view
 *
 * @author SGU
 */
public class UnselectNodesAction extends AbstractInfoWrkspOrgaAction {
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
                @I18nProperty(key = "file.explorer.view.action.unselect.nodes.text", value = "Déselectionner les fichiers"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.unselect.nodes.tooltip",
                value = "Déselectionne tous les fichiers/dossiers de la vue en cours"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.unselect.nodes.icon", value = "/icons/eraser.png"), // Force \n
  })
  public UnselectNodesAction(final FileExplorerView fileExplorerView) {
    super("file.explorer.view.action.unselect.nodes");
    this.fileExplorerView = fileExplorerView;

    setRule(new IsAtLeastViewFileSelectedRule(fileExplorerView));
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    final UnselectAllNodesForAllFileViewServiceUI serviceUI = SpringBeanHelper.getImplementationByInterface(UnselectAllNodesForAllFileViewServiceUI.class);
    serviceUI.execute(fileExplorerView);

  }
}
