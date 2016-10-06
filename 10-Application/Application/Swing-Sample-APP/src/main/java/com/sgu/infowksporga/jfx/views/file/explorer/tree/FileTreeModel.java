package com.sgu.infowksporga.jfx.views.file.explorer.tree;

import javax.swing.tree.TreeNode;

import com.sgu.core.framework.gui.swing.tree.GTreeModel;

/**
 * Description : FileTreeModel class<br>
 *
 */
public class FileTreeModel extends GTreeModel {

  /**
   * The attribute serialVersionUID 
   */
  private static final long serialVersionUID = -2141128317038428510L;

  /**
   * Constructor<br>
   *
   * @param root
   */
  public FileTreeModel(final TreeNode root) {
    super(root);
  }

  /** 
   * {@inheritDoc}
   */
  @Override
  public boolean isLeaf(final Object node) {
    boolean isLeaf = true;

    if (node instanceof FileTreeNode) {
      final FileTreeNode fileTreeNode = (FileTreeNode) node;
      if (fileTreeNode.getFileObject() != null && fileTreeNode.getFileObject().isDirectory()) {
        isLeaf = false;
      }
    }

    return isLeaf;
  }

}
