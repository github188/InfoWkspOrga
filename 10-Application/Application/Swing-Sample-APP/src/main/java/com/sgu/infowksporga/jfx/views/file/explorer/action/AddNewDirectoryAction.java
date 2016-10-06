package com.sgu.infowksporga.jfx.views.file.explorer.action;

import java.awt.event.ActionEvent;
import java.io.File;

import org.apache.commons.io.FileUtils;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.tree.GTreeModel;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTree;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTreeNode;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileVo;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileVoBuilder;

/**
 * Description : Add New Directory Action class<br>
 * 
 * @author SGU
 */
public class AddNewDirectoryAction extends AbstractInfoWrkspOrgaAction {

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
                @I18nProperty(key = "file.explorer.view.action.add.new.directory.text", value = "Créer un nouveau répertoire"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.add.new.directory.tooltip",
                value = "Créer un nouveau répertoire dans celui sélectionné"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.add.new.directory.icon", value = "/icons/folder_add.png"), // Force \n
  })
  public AddNewDirectoryAction(final FileExplorerView fileExplorerView) {
    super("file.explorer.view.action.add.new.directory");
    this.fileExplorerView = fileExplorerView;
  }

  /** {@inheritDoc} */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "new.directory", value = "Nouveau répertoire"), // Force \n
  })
  public void actionPerformed(final ActionEvent evt) {
    try {
      final FileTree fileTree = fileExplorerView.getFileTree();

      FileTreeNode selectedFileTreeNode = (FileTreeNode) fileTree.getLastSelectedPathComponent();
      if (selectedFileTreeNode == null) {
        selectedFileTreeNode = (FileTreeNode) fileTree.getModel().getRoot();
      }

      File selectedFileObject = selectedFileTreeNode.getFileObject();

      if (selectedFileObject.isFile()) {
        selectedFileTreeNode = (FileTreeNode) selectedFileTreeNode.getParent();
        selectedFileObject = selectedFileObject.getParentFile();
      }

      String strFile = selectedFileObject.getPath();
      strFile += "/" + I18nHelperApp.getMessage("new.directory");

      final File file = new File(strFile);
      if (!file.exists()) {
        FileUtils.forceMkdir(file);

        // Update tree only if node is already opened
        if (selectedFileTreeNode.isAlreadyExpanded()) {
          final FileVo fileVo = FileVoBuilder.buildFileVo(new File(strFile));
          final FileTreeNode newFileTreeNode = new FileTreeNode(fileVo);
          selectedFileTreeNode.add(newFileTreeNode);
          ((GTreeModel) fileTree.getModel()).reload(selectedFileTreeNode);
        }
      }

    } catch (final Exception e) {
      throw new TechnicalException(e);
    }

  }
}