package com.sgu.infowksporga.jfx.zfacade.local.edit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgu.core.framework.gui.swing.tree.GTreeNode;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTree;
import com.sgu.infowksporga.jfx.zfacade.local.AbstractInfoWrkspOrgaServiceUI;

import net.infonode.docking.internalutil.InternalDockingUtil;

/**
 * Description : Abstract Paste Files Facade class<br>
 */
public abstract class AbstractPasteFilesServiceUI extends AbstractInfoWrkspOrgaServiceUI {

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPasteFilesServiceUI.class);

  /**
   * Constructor<br>
   */
  public AbstractPasteFilesServiceUI() {
    super();
  }

  /**
   * Description : copyFiles method <br>
   *
   * @param dirDest
   * @param file
   * @throws IOException
   */
  public void copyFiles(final File file, final String dirDest) throws IOException {
    File dirDestFile = new File(dirDest);
    if (dirDestFile.isFile()) {
      dirDestFile = dirDestFile.getParentFile();
    }

    if (!file.getParentFile().equals(dirDestFile)) {
      FileUtils.copyFileToDirectory(file, dirDestFile, true);
    }
    else {
      final String origFilePath = file.getPath();
      int lastIndex = origFilePath.lastIndexOf("\\");
      if (lastIndex == -1) {
        lastIndex = origFilePath.lastIndexOf("/");
      }
      String fileName = origFilePath.substring(lastIndex + 1, origFilePath.length());
      final int lastIndexOfDot = fileName.lastIndexOf(".");
      if (lastIndexOfDot != -1) {
        fileName = fileName.substring(0, lastIndexOfDot) + " - Copy" + fileName.substring(lastIndexOfDot, fileName.length());
      }
      else {
        fileName = fileName + " - Copy";
      }

      FileUtils.copyFile(file, new File(dirDestFile.getPath() + "/" + fileName), true);
    }
  }

  /**
   * Description : refreshScreen after FileTreeTransfertHandler.exportDone() <br>
   */
  public void refreshScreen() {
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {

        final ArrayList<Object> views = new ArrayList<Object>(10);
        // If the current view is null search on all Workspace views
        InternalDockingUtil.getViews(GUISessionProxy.getCurrentWorkspace().getRootWindow(), views);

        for (final Object view : views) {
          if (view instanceof FileExplorerView) {
            final FileExplorerView fileExplorerView = (FileExplorerView) view;
            final FileTree fileTree = fileExplorerView.getFileTree();

            final TreePath[] selectionPaths = fileTree.getSelectionPaths();
            if (selectionPaths != null) {
              GUISessionProxy.getGuiSession().setSessionAttribute("test", selectionPaths);
            }
            final Enumeration expansionState = fileTree.getExpansionState((GTreeNode) fileTree.getModel().getRoot());
            // Force the rebuild of the view
            fileExplorerView.setConfiguration(fileExplorerView.getViewIdentifier());
            fileTree.loadExpansionState(expansionState);

            if (selectionPaths != null) {
              for (int i = 0; i < selectionPaths.length; i++) {
                System.out.println(selectionPaths[i]);
              }
              System.out.println("---------------------------------");

            }

            fileTree.setSelectionPaths(selectionPaths);

          }
        }
      }
    });

  }

}
