package com.sgu.infowksporga.jfx.views.file.explorer.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTree;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTreeNode;

/**
 * Description : ExpandAllDirectoryAction class<br>
 * 
 * @author SGU
 */
public class ExpandAllDirectoryAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The reference to get the directory tree
   */
  private FileTree fileTree;

  /**
   * Store it because tree is null at construction time of the Action
   */
  private FileExplorerView fileExplorerView;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "file.explorer.view.action.expand.text", value = "Ouvrir le noeud"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.expand.tooltip",
                value = "Ouvre le noeud sélectionné ainsi que tous les sous noeuds"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.expand.icon", value = "/icons/expand.png"), // Force \n
  })
  public ExpandAllDirectoryAction(final FileTree fileTree) {
    super("file.explorer.view.action.expand");
    this.fileTree = fileTree;
  }

  /**
   * Constructor<br>
   */
  public ExpandAllDirectoryAction(final FileExplorerView fileExplorerView) {
    this((FileTree) null);
    this.fileExplorerView = fileExplorerView;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    if (fileTree == null) {
      fileTree = fileExplorerView.getFileTree();
    }

    FileTreeNode node = (FileTreeNode) fileTree.getLastSelectedPathComponent();
    if (node == null) {
      node = (FileTreeNode) fileTree.getModel().getRoot();
    }

    if (node.getFileObject().isDirectory()) {
      fileTree.loadAllFileRecursively(node, true);
      fileTree.expandAllNode(FileTree.getPath(node), -1);
    }
  }

}