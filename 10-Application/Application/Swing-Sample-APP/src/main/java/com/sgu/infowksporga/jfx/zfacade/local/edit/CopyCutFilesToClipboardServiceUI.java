package com.sgu.infowksporga.jfx.zfacade.local.edit;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.component.StatusBar;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;

import lombok.Getter;
import lombok.Setter;
import net.infonode.docking.internalutil.InternalDockingUtil;

/**
 * Description : Copy or Cut Files In Clipboard Facade class<br>
 */
@Service
@Getter
@Setter
public class CopyCutFilesToClipboardServiceUI extends AbstractPasteFilesServiceUI {

  /**
   * COPY
   */
  public static final String COPY = "COPY";

  /**
   * CUT
   */
  public static final String CUT = "CUT";

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CopyCutFilesToClipboardServiceUI.class);

  /**
   * Initialize to COPY by default
   */
  private String cutOrCopy;

  /**
   * Constructor<br>
   */
  public CopyCutFilesToClipboardServiceUI() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(Object... params) throws TechnicalException, BusinessException {
    final FileExplorerView currentView = (FileExplorerView) params[0];

    final ArrayList<Object> views = new ArrayList<Object>(10);

    // If the current view is null search on all Workspace views
    if (currentView == null) {
      InternalDockingUtil.getViews(GUISessionProxy.getCurrentWorkspace().getRootWindow(), views);
    }
    else {
      views.add(currentView);
    }

    // Get list of selected files & Directories in Directory Desk views Tree
    final String selectedFiles = EditUtil.getSelectedFilesFromViewAsString(views);

    // Add the list in clipboard
    final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    GUISessionProxy.setLastFileEditionMode(cutOrCopy);

    // Status bar update
    final StatusBar statusBar = GUISessionProxy.getInfoWrkspOrgaFrame().getStatusBar();
    if (COPY.equals(cutOrCopy)) {
      statusBar.getLblCurrentFileEditionMode().setVisible(true);
      statusBar.getLblCurrentFileEditionMode().setIcon(UtilGUI.getImageIconFromClasspath("/icon/copy.png"));
      statusBar.getLblCurrentFileEditionMode().setToolTipText("Edition Mode : COPY");
    }
    else if (CUT.equals(cutOrCopy)) {
      statusBar.getLblCurrentFileEditionMode().setVisible(true);
      statusBar.getLblCurrentFileEditionMode().setIcon(UtilGUI.getImageIconFromClasspath("/icon/cut.png"));
      statusBar.getLblCurrentFileEditionMode().setToolTipText("Edition Mode : CUT");
    }
    else {
      statusBar.getLblCurrentFileEditionMode().setIcon(UtilGUI.getImageIconFromClasspath("/icon/transparent-16-16.png"));
      statusBar.getLblCurrentFileEditionMode().setVisible(false);
    }

    final StringTokenizer tockenizer = new StringTokenizer(selectedFiles, "\n", false);
    final List<File> files = new ArrayList<File>();
    while (tockenizer.hasMoreTokens()) {
      files.add(new File(tockenizer.nextToken()));
    }

    // Create the transferable object to pass it to the clipboard
    final Transferable transferable = new Transferable() {

      @Override
      public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { DataFlavor.javaFileListFlavor };
      }

      @Override
      public boolean isDataFlavorSupported(final DataFlavor flavor) {
        return DataFlavor.javaFileListFlavor.equals(flavor);
      }

      @Override
      public Object getTransferData(final DataFlavor flavor) throws UnsupportedFlavorException, IOException {

        return files;
      }
    };

    clipboard.setContents(transferable, null);

    return null;
  }

}
