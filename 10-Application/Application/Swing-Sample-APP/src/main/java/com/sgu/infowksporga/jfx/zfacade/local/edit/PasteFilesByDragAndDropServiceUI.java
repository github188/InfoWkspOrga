package com.sgu.infowksporga.jfx.zfacade.local.edit;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.swing.TransferHandler;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : Paste Files by Drag & Drop Facade class<br>
 */
@Service
@Getter
@Setter
public class PasteFilesByDragAndDropServiceUI extends AbstractPasteFilesServiceUI {

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(PasteFilesByDragAndDropServiceUI.class);

  /**
   * Constructor<br>
   */
  public PasteFilesByDragAndDropServiceUI() {
    super();
  }

  /**
   * COPY or MOVE
   */
  private int action;

  /**
   * The files
   */
  private List<File> files;

  /**
   * Store the destination
   */
  private File destination;

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(final Object... params) throws TechnicalException, BusinessException {

    try {

      final Iterator i = files.iterator();
      while (i.hasNext()) {
        final File file = (File) i.next();
        if (file.exists()) {

          if (TransferHandler.MOVE == action) {
            if (file.isDirectory()) {
              FileUtils.moveDirectoryToDirectory(file, destination, true);
            }
            else {
              FileUtils.moveFileToDirectory(file, destination, true);
            }
          }
          else { // COPY by default
            if (file.isDirectory()) {
              FileUtils.copyDirectoryToDirectory(file, destination);
            }
            else {
              copyFiles(file, destination.getPath());
            }
          }
        }
      }

    } catch (final Exception e) {
      UtilDlgMessage.error(e.getMessage(), e);
    }

    return null;
  }

}
