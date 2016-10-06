package com.sgu.infowksporga.jfx.views.file.explorer.tree;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.DropMode;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultMutableTreeNode;

import com.sgu.core.framework.collection.MultiSortCollection;
import com.sgu.core.framework.collection.sort.SortElement;
import com.sgu.core.framework.collection.sort.SortModel;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.tree.GTree;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.dnd.FileTreeTransfertHandler;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.listener.FileTreeExpandListener;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.listener.FileTreeMouseListener;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.listener.FileTreeNodeKeyListener;

/**
 * Description : FileTree class<br>
 */
public class FileTree extends GTree {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -7993259403150638286L;

  /**
   * The tree renderer
   */
  private static final FileTreeViewRenderer TREE_RENDERER = new FileTreeViewRenderer();

  /**
   * This node is used to allow '+' icon be displayed
   * When user select the folder, this temporary node is removed
   * and replaced by the real folder children
   */
  public static final FakeTreeNode CHILD_FAKE = new FakeTreeNode();

  /**
   * Constructor<br>
   *
   * @param root a <code>TreeNode</code> object
   */
  public FileTree(final FileTreeModel fileTreeModel) {
    super(fileTreeModel);

    initializeTree();
  }

  /**
   * Description : initializeTree method <br>
   */
  private void initializeTree() {
    // Register the tree with the ToolTipManager (which isn’t necessary for nearly all other Swing Components…)
    ToolTipManager.sharedInstance().registerComponent(this);

    setShowsRootHandles(false);
    setCellRenderer(TREE_RENDERER);
    setCellEditor(new FileTreeCellEditor(this, TREE_RENDERER));

    // Drag & Drop
    setDragEnabled(true);
    setDropMode(DropMode.ON_OR_INSERT);
    setTransferHandler(new FileTreeTransfertHandler());

    // Enable Tree tooltip manager
    ToolTipManager.sharedInstance().registerComponent(this);

    // Initialize listener for lazy loading
    addTreeWillExpandListener(new FileTreeExpandListener(this));

    // Add mouse listener to open file or directory with native windows environment
    addMouseListener(buildFileTreeMouseListener());

    // Add key listener
    addKeyListener(new FileTreeNodeKeyListener(this));
  }

  /**
   * Description : buildFileTreeMouseListener method <br>
   * This method allow to laod different class at runtime to customize file tree menu items
   *
   * @return
   */
  private FileTreeMouseListener buildFileTreeMouseListener() {
    //final String className = I18nHelperApp.getMessage("view.directory.FileTreeMouseListener.class");
    FileTreeMouseListener listener = null;
    try {
      //final Constructor<?> listenerConstructor = Class.forName(className).getConstructors()[0];
      listener = new FileTreeMouseListener(this); //listenerConstructor.newInstance(this);
    } catch (final Exception e) {
      throw new TechnicalException(e);
    }

    return listener;
  }

  /**
   * Description : loadAllFileRecursively method <br>
   *
   * @param treeNode the tree node to load
   * @param allHierarchy if set to true get recursively all children
   */
  @SuppressWarnings("unchecked")
  public void loadAllFileRecursively(final FileTreeNode treeNode, final boolean allHierarchy) {
    // Remove temporary child
    if (!treeNode.isAlreadyExpanded()) {
      final int childIndex = treeNode.getIndex(CHILD_FAKE);
      if (childIndex >= 0) {
        treeNode.remove(childIndex);
      }
    }

    /*
     * Get child list from File object if treeNode has not been already open
     */
    final List<FileTreeNode> childList = new ArrayList<FileTreeNode>(treeNode.countChildren());
    if (treeNode.isAlreadyExpanded()) {
      final Enumeration<FileTreeNode> enumeration = treeNode.children();
      while (enumeration.hasMoreElements()) {
        childList.add(enumeration.nextElement());
      }
    }
    else {
      final MultiSortCollection fileCollection = new MultiSortCollection(treeNode.getFileObject().listFiles());

      for (int i = 0; i < fileCollection.size(); i++) {
        final FileVo fileVo = FileVoBuilder.buildFileVo((File) fileCollection.get(i));
        childList.add(new FileTreeNode(fileVo));
      }

      // Sort directory and file
      final SortModel sortModel = new SortModel();
      //sortModel.add(new SortElement("filePath", false));
      sortModel.add(new SortElement("fileName", true));
      new MultiSortCollection(childList).sortCollection(sortModel);
    }

    for (final FileTreeNode childTreeNode : childList) {
      // Manage directory first
      if (childTreeNode.getFileObject().isDirectory()) {
        if (allHierarchy) {
          loadAllFileRecursively(childTreeNode, allHierarchy);
        }
        else if (!treeNode.isAlreadyExpanded()) {
          childTreeNode.add(CHILD_FAKE); // Add a temporary node to display expand Icon
        }
      }

      // Add only child if it has not been already added
      if (!treeNode.isAlreadyExpanded()) {
        treeNode.add(childTreeNode);
      }
    }

    treeNode.setAlreadyExpanded(true);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getNodeValue(final DefaultMutableTreeNode treeNode) {
    if (treeNode != null && treeNode.getUserObject() != null) {
      return ((FileVo) treeNode.getUserObject()).getFile().getName();
    }

    return null;
  }
}
