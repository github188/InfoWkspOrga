package com.sgu.infowksporga.jfx.zfacade.local;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTree;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTreeNode;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileVo;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileVoBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : Rename File Or Directory Facade class<br>
 */
@Service
@Setter
@Getter
public class RenameFileDirectoryServiceUI extends AbstractInfoWrkspOrgaServiceUI {

  /**
   * The logger.
   */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(RenameFileDirectoryServiceUI.class);

  /**
   * Store the reference to the current tree
   */
  private FileTree fileTree;

  /**
   * new File Name
   */
  private String newFileName;

  /**
   * Constructor<br>
   */
  public RenameFileDirectoryServiceUI() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(Object... container) throws TechnicalException, BusinessException {
    final FileTreeNode node = (FileTreeNode) fileTree.getLastSelectedNode();
    FileVo fileVo = (FileVo) node.getUserObject();

    final File fileOld = fileVo.getFile();

    if (!fileOld.getName().equals(newFileName)) {
      int indexOfLastSlash = fileOld.getPath().lastIndexOf("/");
      if (indexOfLastSlash == -1) {
        indexOfLastSlash = fileOld.getPath().lastIndexOf("\\");
      }
      final String newFilePathName = UtilString.replaceString(fileOld.getPath(), indexOfLastSlash + 1, fileOld.getPath().length(),
                                                              newFileName);
      final File fileNew = new File(newFilePathName);

      final boolean result = fileOld.renameTo(fileNew);
      if (result == false) {
        UtilDlgMessage.warning("file.explorer.view.warning.file.not.renamed");
      }
      else {
        // Update VO
        final File fileRenamed = new File(newFilePathName);
        fileVo = FileVoBuilder.buildFileVo(fileRenamed);
        GUISessionProxy.setLastSelectedFile(fileRenamed);
      }

    }
    return fileVo;
  }

}
