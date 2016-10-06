package com.sgu.infowksporga.jfx.views.file.explorer.tree.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.sgu.core.framework.exception.TechnicalException;

/**
 * Description : FileTreeNodeTransferable class<br>
 *
 * @author SGU
 */
public class FileTreeNodeTransferable implements Transferable {
  // The file representation to transfer 
  private final List<File> dataFiles;

  // Can store java.io.File
  private final DataFlavor[] flavors = new DataFlavor[1];

  public FileTreeNodeTransferable(final List<File> files) {
    super();

    dataFiles = files;
    flavors[0] = DataFlavor.javaFileListFlavor;
  }

  @Override
  public DataFlavor[] getTransferDataFlavors() {
    return flavors;
  }

  @Override
  public boolean isDataFlavorSupported(final DataFlavor flavor) {
    return DataFlavor.javaFileListFlavor.equals(flavor);
  }

  @Override
  public List<?> getTransferData(final DataFlavor flavor) throws UnsupportedFlavorException, IOException {
    if (flavor.equals(DataFlavor.javaFileListFlavor)) {
      return dataFiles;
    } else {
      throw new TechnicalException("Flavor not Supported");
    }
  }

}