package com.sgu.infowksporga.jfx.views.file.explorer.tree.listener;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;

import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTree;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTreeNode;

/**
 * Description : TreeExpandListener class<br>
 *
 */
public class FileTreeExpandListener implements TreeWillExpandListener {
  /**
   * Reference to the file tree
   */
  private final FileTree fileTree;

  /**
   * Constructor<br>
   *
   * @param fileTree Reference to the file tree
   */
  public FileTreeExpandListener(final FileTree fileTree) {
    super();
    this.fileTree = fileTree;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void treeWillExpand(final TreeExpansionEvent event) throws ExpandVetoException {
    final Object obj = event.getPath().getLastPathComponent();

    if (obj != null && obj instanceof FileTreeNode) {
      final FileTreeNode node = (FileTreeNode) obj;
      if (!node.isAlreadyExpanded() && UtilString.isBlank(fileTree.getFilteredText())) {
        fileTree.loadAllFileRecursively(node, false);
      }
    }
  }

  @Override
  public void treeWillCollapse(final TreeExpansionEvent event) throws ExpandVetoException {

  }

}
