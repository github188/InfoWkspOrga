package com.sgu.infowksporga.jfx.zfacade.local.edit;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : Paste Files From Clipboard Facade class<br>
 */
@Service
@Getter
@Setter
public class PasteFilesFromClipboardServiceUI extends AbstractPasteFilesServiceUI {

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(PasteFilesFromClipboardServiceUI.class);

  /**
   * Constructor<br>
   */
  public PasteFilesFromClipboardServiceUI() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(final Object... params) throws TechnicalException, BusinessException {
    // Get the list from clipboard
    final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    final Transferable clipData = clipboard.getContents(clipboard);

    if (clipData != null) {
      try {

        // First check if a destination directory exists
        String dirDest = "";

        if (GUISessionProxy.getLastSelectedFile() != null) {
          dirDest = GUISessionProxy.getLastSelectedFile().getPath();
        }
        else {
          UtilDlgMessage.error("Must select a directory", "");
          return null;
        }

        // Manage only copy from Other application (like windows explorer)
        if (clipData.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
          final String action = GUISessionProxy.getLastFileEditionMode();

          final List data = (List) clipData.getTransferData(DataFlavor.javaFileListFlavor);
          final Iterator i = data.iterator();
          while (i.hasNext()) {
            final File file = (File) i.next();
            if (file.exists()) {

              if (CopyCutFilesToClipboardServiceUI.CUT.equals(action)) {
                if (file.isDirectory()) {
                  FileUtils.moveDirectoryToDirectory(file, new File(dirDest), true);
                }
                else {
                  FileUtils.moveFileToDirectory(file, new File(dirDest), true);
                }
              }
              else {
                if (file.isDirectory()) {
                  FileUtils.copyDirectoryToDirectory(file, new File(dirDest));
                }
                else {
                  copyFiles(file, dirDest);
                }
              }
            }
          }
        }

      } catch (final Exception e) {
        UtilDlgMessage.error(e.getMessage(), e);
      }
    }

    return null;
  }

}
