package com.sgu.infowksporga.jfx.zfacade.local.edit;

import java.util.ArrayList;
import java.util.List;

import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileVo;

/**
 * Description : EditUtil class<br>
 */
public final class EditUtil {

  /**
   * Constructor<br>
   */
  private EditUtil() {
  }

  /**
   * Description : getSelectedFilesFromView method <br>
   *
   * @return the list of file and selected directories separated by "\n"
   */
  public static String getSelectedFilesFromViewAsString(final ArrayList<Object> views) {
    String selectedFiles = "";
    for (final Object view : views) {
      if (view instanceof FileExplorerView) {
        final FileExplorerView directoryView = (FileExplorerView) view;
        final List<Object> userObjects = directoryView.getFileTree().getSelectedUserObject();
        if (userObjects != null) {
          for (final Object userObject : userObjects) {
            selectedFiles += (((FileVo) userObject).getFile().getPath()) + "\n";
          }
        }
      }
    }

    return selectedFiles;
  }

  /**
   * Description : getSelectedFilesFromView method <br>
   *
   * @return the list of file and selected directories separated by "\n"
   */
  public static List<String> getSelectedFilesFromView(final ArrayList<Object> views) {
    final List<String> selectedFiles = new ArrayList<String>(10);
    for (final Object view : views) {
      if (view instanceof FileExplorerView) {
        final FileExplorerView directoryView = (FileExplorerView) view;
        final List<Object> userObjects = directoryView.getFileTree().getSelectedUserObject();
        if (userObjects != null) {
          for (final Object userObject : userObjects) {
            selectedFiles.add((((FileVo) userObject).getFile().getPath()));
          }
        }
      }
    }

    return selectedFiles;
  }

}
