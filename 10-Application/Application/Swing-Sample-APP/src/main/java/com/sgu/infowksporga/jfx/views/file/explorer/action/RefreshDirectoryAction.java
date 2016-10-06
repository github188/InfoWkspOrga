package com.sgu.infowksporga.jfx.views.file.explorer.action;

import java.awt.event.ActionEvent;
import java.util.Enumeration;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.core.framework.gui.swing.tree.GTreeNode;
import com.sgu.core.framework.gui.swing.util.UtilNotificationMsg;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTree;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTreeNode;

/**
 * Description : RefreshDirectoryAction class<br>
 *
 * @author SGU
 */
public class RefreshDirectoryAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The reference to get the directory tree
   */
  private final FileExplorerView fileExplorerView;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "file.explorer.view.action.refresh.directory.text", value = "Rafraîchir"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.refresh.directory.tooltip",
                value = "Rafraîchit le contenu du répertoire sélectionné"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.refresh.directory.icon", value = "/icons/refresh.png"), // Force \n
  })
  public RefreshDirectoryAction(final FileExplorerView fileExplorerView) {
    super("file.explorer.view.action.refresh.directory");
    this.fileExplorerView = fileExplorerView;
  }

  /** {@inheritDoc} */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "file.explorer.view.reload.completed", value = "Le rechargement de la vue est terminé"), // Force \n
  })
  public void actionPerformed(final ActionEvent arg0) {
    final FileTree fileTree = fileExplorerView.getFileTree();
    final GTextField txtFilter = fileExplorerView.getTxtFilter();

    final Enumeration expansionState = fileTree.getExpansionState((GTreeNode) fileTree.getModel().getRoot());

    // Force the rebuild of the view
    fileExplorerView.setConfiguration(fileExplorerView.getViewIdentifier());

    if (!UtilString.isBlank(fileExplorerView.getTxtFilter().getText())) {
      // Re-apply filter if exist
      fileTree.loadAllFileRecursively((FileTreeNode) fileExplorerView.getFileTree().getModel().getRoot(), true);
      fileTree.filterTree(txtFilter.getText(), fileExplorerView.getChkFilter().isSelected());
      fileTree.expandAllNode();

    }
    else {
      fileTree.loadExpansionState(expansionState);
    }

    UtilNotificationMsg.displayMessage("file.explorer.view.reload.completed");

  }
}