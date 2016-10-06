package com.sgu.infowksporga.jfx.views.file.explorer.tree;

import java.io.File;

import org.joda.time.DateTime;

import com.sgu.core.framework.gui.swing.util.UtilGUI;

/**
 * Description : FileVoBuilder class<br>
 */
public final class FileVoBuilder {

  /**
   * Constructor<br>
   */
  private FileVoBuilder() {
  }

  /**
   * Description : buildFileVo method <br>
   *
   * @param file the file object to transform in FileVo
   * @return FileVo
   */
  public static FileVo buildFileVo(final File file) {
    final FileVo fileVo = new FileVo();
    fileVo.setFile(file);
    fileVo.setFileIcon(UtilGUI.FILE_SYSTEM_VIEW.getSystemIcon(file));

    /* Causes performance problem in network files access
     final String size = FileUtils.byteCountToDisplaySize(file.length());
    fileVo.setFileSize(size);
    */

    final DateTime dateTime = new DateTime(file.lastModified());
    fileVo.setLastModifiedDate(dateTime.toString());

    return fileVo;
  }

}
