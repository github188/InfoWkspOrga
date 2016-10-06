package com.sgu.infowksporga.jfx.views.file.explorer.rules;

import java.util.ArrayList;
import java.util.List;

import com.sgu.core.framework.gui.swing.rule.AbstractComponentRule;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.zfacade.local.edit.EditUtil;

import net.infonode.docking.internalutil.InternalDockingUtil;

/**
 * Description : IsAtLeastViewFileSelectedRule class<br>
 */
public class IsAtLeastViewFileSelectedRule extends AbstractComponentRule {

  /**
   * The directory to inspect
   */
  private final FileExplorerView fileExplorerView;

  /**
   * Constructor<br>
   * 
   * @param fileExplorerView The directory to inspect
   */
  public IsAtLeastViewFileSelectedRule(final FileExplorerView fileExplorerView) {
    super();
    this.fileExplorerView = fileExplorerView;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void executeRule() {
    // Get current workspace

    if (GUISessionProxy.getCurrentWorkspace() == null || GUISessionProxy.getCurrentWorkspace().getWorkspaceDto() == null) {
      getTargetComponent().setEnabled(false);
    }
    else {
      boolean isEnabled = true;

      final ArrayList<Object> views = new ArrayList<Object>(10);
      if (fileExplorerView == null) {
        InternalDockingUtil.getViews(GUISessionProxy.getCurrentWorkspace().getRootWindow(), views);
      }
      else {
        views.add(fileExplorerView);
      }

      List<String> selectedFiles = new ArrayList<String>();
      // Get list of selected files & Directories in Directory Desk views Tree
      selectedFiles = EditUtil.getSelectedFilesFromView(views);

      // Check at least one file is selected
      if (selectedFiles.size() == 0) {
        isEnabled = false;
      }

      getTargetComponent().setEnabled(isEnabled);
    }

  }
}
