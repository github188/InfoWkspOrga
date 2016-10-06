package com.sgu.infowksporga.jfx.views.file.explorer.action;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.rules.IsAtLeastViewFileSelectedRule;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTree;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTreeNode;

/**
 * Description : CopyFileNameAction class<br>
 * This Action copy the selected file name to the clipboard
 * 
 * @author SGU
 */
public class CopyFileNameToClipboardAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The reference to get the directory tree
   */
  private final FileTree fileTree;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "file.explorer.view.action.copy.fileName.text", value = "Copier le nom de l'élément"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.copy.fileName.tooltip",
                value = "Copie le nom du fichier ou du dossier sélectionné dans le presse papier"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.copy.fileName.icon", value = "/icons/copy.png"), // Force \n
  })
  public CopyFileNameToClipboardAction(final FileTree fileTree) {
    super("file.explorer.view.action.copy.fileName");
    this.fileTree = fileTree;

    setRule(new IsAtLeastViewFileSelectedRule((FileExplorerView) UtilGUI.getParentComponent(fileTree, FileExplorerView.class)));
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {

    FileTreeNode node = (FileTreeNode) fileTree.getLastSelectedPathComponent();
    if (node == null) {
      node = (FileTreeNode) fileTree.getModel().getRoot();
    }

    final Toolkit toolkit = Toolkit.getDefaultToolkit();
    final Clipboard clipboard = toolkit.getSystemClipboard();
    final StringSelection strSel = new StringSelection(node.getFileObject().getPath());
    clipboard.setContents(strSel, null);

  }
}