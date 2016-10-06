package com.sgu.infowksporga.jfx.views.file.explorer.tree.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTree;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTreeNode;

/**
 * Description : FilterKeyListener class<br>
 */
public class FileTreeNodeKeyListener implements KeyListener {

  /**
   * Ref to the tree to filter on
   */
  private final FileTree fileTree;

  /**
   * Constructor<br>
   */
  public FileTreeNodeKeyListener(final FileTree fileTree) {
    this.fileTree = fileTree;
  }

  /**
   * Gets the selected tree node.
   *
   * @return the selected tree node
   */
  protected FileTreeNode getSelectedTreeNode() {
    FileTreeNode node = (FileTreeNode) fileTree.getLastSelectedPathComponent();
    if (node == null) {
      node = (FileTreeNode) fileTree.getModel().getRoot();
    }
    return node;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void keyPressed(final KeyEvent evt) {
    final int key = evt.getKeyCode();

    if (key == KeyEvent.VK_ENTER) {
      final FileTreeNode node = getSelectedTreeNode();
      UtilInfoWrkspOrga.displayWithDesktopTool(node.getFileObject().toString());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void keyReleased(final KeyEvent e) {
    final FileTreeNode node = getSelectedTreeNode();
    GUISessionProxy.getInfoWrkspOrgaFrame().getStatusBar().getLblMessage().setText(node.getFileObject().toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void keyTyped(final KeyEvent evt) {
    // Not implemented
  }

}
