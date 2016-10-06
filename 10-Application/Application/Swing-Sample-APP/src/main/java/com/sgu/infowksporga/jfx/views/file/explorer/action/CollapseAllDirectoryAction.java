package com.sgu.infowksporga.jfx.views.file.explorer.action;

import java.awt.event.ActionEvent;

import javax.swing.tree.TreePath;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTree;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTreeNode;

/**
 * Description : CollapseAllAction class<br>
 * 
 * @author SGU
 */
public class CollapseAllDirectoryAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The reference to get the directory tree
   */
  private FileTree fileTree;

  /**
   * The reference to get the directory tree
   */
  private FileExplorerView fileExplorerView;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "file.explorer.view.action.collapse.text", value = "Plier le noeud"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.collapse.tooltip",
                value = "Plie le noeud sélectionné ainsi que tous les sous noeuds"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.collapse.icon", value = "/icons/collapse.png"), // Force \n
  })
  public CollapseAllDirectoryAction(final FileTree fileTree) {
    super("file.explorer.view.action.collapse");
    this.fileTree = fileTree;
  }

  /**
   * Constructor<br>
   */
  public CollapseAllDirectoryAction(final FileExplorerView fileExplorerView) {
    this((FileTree) null);
    this.fileExplorerView = fileExplorerView;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent arg0) {
    if (fileTree == null) {
      fileTree = fileExplorerView.getFileTree();
    }

    FileTreeNode node = (FileTreeNode) fileTree.getLastSelectedPathComponent();
    if (node == null) {
      node = (FileTreeNode) fileTree.getModel().getRoot();
    }

    if (node.getFileObject().isDirectory()) {
      if (node.equals(fileTree.getModel().getRoot())) {
        fileTree.collapseAllNode();
        fileTree.expandAllNode(1);
      }
      else {
        fileTree.collapseAll(new TreePath(node), 1);
      }
    }
  }
}