package com.sgu.infowksporga.jfx.views.file.explorer.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.UtilFileTreeFilter;

/**
 * Description : FilterKeyListener class<br>
 */
public class FileTreeFilterListener implements ActionListener {

  /**
   * Ref to the tree to filter on
   */
  private final FileExplorerView fileExplorerView;

  /**
   * Constructor<br>
   */
  public FileTreeFilterListener(final FileExplorerView fileExplorerView) {
    this.fileExplorerView = fileExplorerView;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void actionPerformed(final ActionEvent e) {
    UtilFileTreeFilter.applyFilter(fileExplorerView);
  }
}
