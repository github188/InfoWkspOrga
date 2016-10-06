package com.sgu.infowksporga.jfx.views.file.explorer.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTree;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTreeNode;

/**
 * Description : OpenFileAction class<br>
 * This Action allow opening folder in explorer or file in OS define Application (ie. : .xls in Excel)
 * 
 * @author SGU
 */
public class OpenFileAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The reference to get the directory tree
   */
  private final FileExplorerView fileExplorerView;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "file.explorer.view.action.open.file.or.directory.text", value = "Ouvrir l'élement sélectionné"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.open.file.or.directory.tooltip",
                value = "Ouvre le fichier ou le dossier sélectionné"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.open.file.or.directory.icon", value = "/icons/file/open.png"), // Force \n
  })
  public OpenFileAction(final FileExplorerView fileExplorerView) {
    super("file.explorer.view.action.open.file.or.directory");
    this.fileExplorerView = fileExplorerView;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent arg0) {
    final FileTree fileTree = fileExplorerView.getFileTree();

    FileTreeNode nodeToOpen = (FileTreeNode) fileTree.getLastSelectedPathComponent();
    if (nodeToOpen == null) {
      nodeToOpen = (FileTreeNode) fileTree.getModel().getRoot();
    }

    UtilInfoWrkspOrga.displayWithDesktopTool(nodeToOpen.getFileObject().toString());

  }
}