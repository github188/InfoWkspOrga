package com.sgu.infowksporga.jfx.views.file.explorer.tree.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.TreePath;

import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.tree.GTree;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTreeNode;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileVo;
import com.sgu.infowksporga.jfx.zfacade.local.edit.PasteFilesByDragAndDropServiceUI;

/**
 * Description : FileTreeTransfertHandler class<br>
 */
public class FileTreeTransfertHandler extends TransferHandler {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 5701148338932024674L;

  /**
   * Constructor<br>
   */
  public FileTreeTransfertHandler() {

  }

  @Override
  public int getSourceActions(final JComponent c) {
    return COPY_OR_MOVE;
  }

  @Override
  protected Transferable createTransferable(final JComponent c) {
    final GTree tree = (GTree) c;

    // Get all selected files in tree and store it to the transferable object
    final List<Object> userObjects = tree.getSelectedUserObject();
    if (userObjects != null) {
      final List<File> files = new ArrayList<File>(userObjects.size());
      if (userObjects != null) {
        for (final Object userObject : userObjects) {
          files.add(((FileVo) userObject).getFile());
        }
      }

      return new FileTreeNodeTransferable(files);
    }

    return null;
  }

  @Override
  public boolean canImport(final TransferHandler.TransferSupport support) {
    if (!support.isDrop()) {
      return false;
    }

    support.setShowDropLocation(true);
    if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
      return false;
    }

    // Do not allow a drop on the drag source selections.   
    final GTree.DropLocation dl = (GTree.DropLocation) support.getDropLocation();
    final GTree tree = (GTree) support.getComponent();
    final int dropRow = tree.getRowForPath(dl.getPath());
    final int[] selRows = tree.getSelectionRows();

    for (int i = 0; selRows != null && i < selRows.length; i++) {
      if (selRows[i] == dropRow) {
        return false;
      }
    }

    return true;
  }

  @Override
  public boolean importData(final TransferHandler.TransferSupport support) {
    final Transferable data = support.getTransferable();
    List<File> files;
    try {
      files = (List<File>) data.getTransferData(DataFlavor.javaFileListFlavor);
    } catch (final Exception e) {
      throw new TechnicalException(e);
    }

    // Get drop location info inside the tree.  
    final JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
    final TreePath dest = dl.getPath();
    final FileTreeNode parent = (FileTreeNode) dest.getLastPathComponent();
    final FileVo vo = (FileVo) parent.getUserObject();

    // Call the service
    PasteFilesByDragAndDropServiceUI serviceUI = SpringBeanHelper.getImplementationByInterface(PasteFilesByDragAndDropServiceUI.class);
    serviceUI.setFiles(files);
    serviceUI.setAction(support.getUserDropAction());
    serviceUI.setDestination(vo.getFile());
    serviceUI.execute();

    return true;
  }

  @Override
  protected void exportDone(final JComponent source, final Transferable data, final int action) {

    //facade.refreshScreen();

  }

  @Override
  public String toString() {
    return getClass().getName();
  }

}
