package com.sgu.infowksporga.jfx.views.file.explorer.tree.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import com.sgu.core.framework.gui.swing.rule.ComponentRuleManager;
import com.sgu.core.framework.gui.swing.separator.GSeparator;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.action.CopyFileNameToClipboardAction;
import com.sgu.infowksporga.jfx.views.file.explorer.action.CopyFilePathToClipboardAction;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTree;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTreeNode;

/**
 * Description : Open File By Dekstop, Display Popup menu item<br>
 */
public class FileTreeMouseListener extends MouseAdapter {
  /**
   * Reference to the tree
   */
  protected final FileTree fileTree;

  /**
   * Store ref popup menu
   */
  protected JPopupMenu popupMenu;

  /**
   * Constructor<br>
   *
   * @param fileTree Reference to the tree
   */
  public FileTreeMouseListener(final FileTree fileTree) {
    super();
    this.fileTree = fileTree;
  }

  /**
   * Description : initPopupMenu method <br>
   */
  protected void buildPopupMenu() {
    popupMenu = new JPopupMenu();

    final CopyFileNameToClipboardAction copyFileName = new CopyFileNameToClipboardAction(fileTree);
    final CopyFilePathToClipboardAction copyFilePath = new CopyFilePathToClipboardAction(fileTree);

    final FileExplorerView parentTree = (FileExplorerView) UtilGUI.getParentComponent(fileTree, FileExplorerView.class);

    popupMenu.add(parentTree.getUnselectNodesAction().createMenuItem(true, true));
    popupMenu.add(parentTree.getCutFilesToClipboardAction().createMenuItem(true, true));
    popupMenu.add(parentTree.getCopyFilesToClipboardAction().createMenuItem(true, true));
    popupMenu.add(parentTree.getPasteFilesAction().createMenuItem(true, true));
    popupMenu.add(parentTree.getRemoveFilesToTrashAction().createMenuItem(true, true));

    popupMenu.add(new GSeparator());

    popupMenu.add(copyFileName.createMenuItem(true, true));
    popupMenu.add(copyFilePath.createMenuItem(true, true));

    popupMenu.add(new GSeparator());
    popupMenu.add(parentTree.getCollapseAction().createMenuItem(true, true));
    popupMenu.add(parentTree.getExpandAction().createMenuItem(true, true));

    popupMenu.setOpaque(true);
    popupMenu.setLightWeightPopupEnabled(true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void mousePressed(final MouseEvent e) {
    final int selRow = fileTree.getRowForLocation(e.getX(), e.getY());

    if (selRow != -1) {
      // Open File or folder
      if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
        final TreePath selPath = fileTree.getPathForLocation(e.getX(), e.getY());
        final FileTreeNode node = (FileTreeNode) selPath.getLastPathComponent();

        if (!node.getFileObject().isDirectory()) {
          UtilInfoWrkspOrga.displayWithDesktopTool(node.getFileObject().toString());
        }

      }
      else if (e.getClickCount() == 1 && SwingUtilities.isRightMouseButton(e)) {
        buildPopupMenu();
        popupMenu.show((JComponent) e.getSource(), e.getX(), e.getY());
        // Rebuild rules
        ComponentRuleManager.executeRuleOnAllComponent(GUISession.getInstance().getApplicationFrame());

      }
      else if (e.getClickCount() == 1 && SwingUtilities.isLeftMouseButton(e)) {
        final TreePath selPath = fileTree.getPathForLocation(e.getX(), e.getY());
        final FileTreeNode node = (FileTreeNode) selPath.getLastPathComponent();
        GUISessionProxy.setLastSelectedFile(node.getFileObject());
        GUISessionProxy.getInfoWrkspOrgaFrame().getStatusBar().getLblMessage().setText(node.getFileObject().toString());
      }

    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void mouseClicked(final MouseEvent e) {
    // Rebuild rules
    ComponentRuleManager.executeRuleOnAllComponent(GUISession.getInstance().getApplicationFrame());
  }

}
