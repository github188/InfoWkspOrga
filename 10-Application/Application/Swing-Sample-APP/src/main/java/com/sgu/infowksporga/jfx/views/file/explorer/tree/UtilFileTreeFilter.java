package com.sgu.infowksporga.jfx.views.file.explorer.tree;

import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;

/**
 * The Class UtilFileTreeFilter.
 */
public class UtilFileTreeFilter {

  /**
   * The Constructor.
   */
  public UtilFileTreeFilter() {
  }

  /**
   * Apply filter.
   *
   * @param fileExplorerView the directory desk view
   */
  public static void applyFilter(final FileExplorerView fileExplorerView) {
    final boolean onLeaf = fileExplorerView.getChkFilter().isSelected();

    final FileTree fileTree = fileExplorerView.getFileTree();
    final GTextField textField = fileExplorerView.getTxtFilter();

    final String filterTxt = textField.getText();
    if (UtilString.isNotBlank(filterTxt)) {
      fileTree.loadAllFileRecursively((FileTreeNode) fileTree.getModel().getRoot(), true);
      fileTree.filterTree(filterTxt, onLeaf);
      fileTree.expandAllNode();
    }
    else {
      fileTree.filterTree("", onLeaf);
      fileTree.collapseAllNode();
      fileTree.expandAllNode(1);
    }
  }

}
