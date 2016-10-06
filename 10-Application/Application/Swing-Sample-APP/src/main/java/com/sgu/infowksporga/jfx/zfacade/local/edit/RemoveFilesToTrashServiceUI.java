package com.sgu.infowksporga.jfx.zfacade.local.edit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.dialog.GMessageBox;
import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;
import com.sgu.core.framework.gui.swing.tree.GTree;
import com.sgu.core.framework.gui.swing.tree.GTreeModel;
import com.sgu.core.framework.gui.swing.tree.GTreeNode;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileVo;
import com.sgu.infowksporga.jfx.zfacade.local.AbstractInfoWrkspOrgaServiceUI;

import lombok.Getter;
import lombok.Setter;
import net.infonode.docking.internalutil.InternalDockingUtil;;

/**
 * Description : Remove Files To Trash Facade class<br>
 */
@Service
@Getter
@Setter
public class RemoveFilesToTrashServiceUI extends AbstractInfoWrkspOrgaServiceUI {

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(RemoveFilesToTrashServiceUI.class);

  /**
   * Constructor<br>
   */
  public RemoveFilesToTrashServiceUI() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(final Object... params) throws TechnicalException, BusinessException {

    final FileExplorerView currentView = (FileExplorerView) params[0];

    final ArrayList<Object> views = new ArrayList<Object>(10);

    // If the current view is null search on all Workspace views
    if (currentView == null) {
      InternalDockingUtil.getViews(GUISessionProxy.getCurrentWorkspace().getRootWindow(), views);
    }
    else {
      views.add(currentView);
    }

    final String selectedFiles = EditUtil.getSelectedFilesFromViewAsString(views);
    final int choice = UtilDlgMessage.question(I18nHelperApp.getMessage("file.explorer.view.remove.files.question", selectedFiles),
                                               GMessageBox.OK | GMessageBox.CANCEL, 500);

    if (choice == GMessageBox.OK) {
      try {
        // Refresh node from tree
        for (final Object view : views) {
          if (view instanceof FileExplorerView) {
            final FileExplorerView directoryView = (FileExplorerView) view;
            final GTree tree = directoryView.getFileTree();
            final List<GTreeNode> nodes = tree.getSelectedNodes();

            if (nodes != null && nodes.size() > 0) {
              for (final GTreeNode node : nodes) {
                final GTreeNode parent = (GTreeNode) node.getParent();
                if (parent != null) { // if null node is = to tree ROOT

                  if (!node.isRoot()) {
                    final FileVo fileVo = (FileVo) node.getUserObject();
                    final String path = fileVo.getFile().getPath();
                    final File file = new File(path);
                    if (file.exists()) {
                      if (file.isDirectory()) {
                        FileUtils.deleteDirectory(file);
                      }
                      else {
                        file.delete();
                      }

                      parent.remove(node);
                      ((GTreeModel) tree.getModel()).reload(parent);
                    }
                  }
                }
              }
            }
          }
        }

      } catch (final Exception e) {
        throw new TechnicalException(e);
      }

    }
    return null;
  }

}
