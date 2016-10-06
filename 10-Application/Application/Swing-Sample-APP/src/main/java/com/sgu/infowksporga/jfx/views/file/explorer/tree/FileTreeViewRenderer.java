package com.sgu.infowksporga.jfx.views.file.explorer.tree;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.tree.GTree;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

/**
 * Description : FileTreeViewRenderer class<br>
 */
public class FileTreeViewRenderer extends DefaultTreeCellRenderer {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -4712263397856982288L;

  /**
   * {@inheritDoc}
   */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "file.explorer.view.filter.apply", value = "Un filtre est appliqué sur cette vue"), // Force \n
  })
  public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean sel, final boolean expanded,
  final boolean leaf, final int row, final boolean hasFocus) {

    if (row == 0) {
      if (!UtilString.isBlank(((GTree) tree).getFilteredText())) {
        final JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        label.setIcon(UtilGUI.getImageIconFromClasspath("/icons/table/filter.png"));
        label.setToolTipText(I18nHelperApp.getMessage("file.explorer.view.filter.apply"));

        return label;
      }
    }

    if (value instanceof FileTreeNode && !((FileTreeNode) value).getFileObject().isDirectory()) {
      final JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
      final FileTreeNode treeNode = (FileTreeNode) value;
      final FileVo fileVo = (FileVo) treeNode.getUserObject();

      // Manage file icon
      label.setIcon(fileVo.getFileIcon());

      // Build the file tooltip
      if (fileVo.getLastModifiedDate() != null || fileVo.getFileSize() != null) {
        final StringBuilder tooltipFile = new StringBuilder();
        tooltipFile.append("<HTML><BODY>");

        if (fileVo.getLastModifiedDate() != null) {
          final String lastModifiedDate = "Date : <b>" + fileVo.getLastModifiedDate() + "</b>";
          tooltipFile.append(lastModifiedDate);
        }

        if (fileVo.getFileSize() != null) {
          final String size = "&nbsp;&nbsp;&nbsp;&nbsp;Size : <b>" + fileVo.getFileSize() + "</b>";
          tooltipFile.append(size);
        }

        tooltipFile.append("</HTML></BODY>");
        label.setToolTipText(tooltipFile.toString());
      }
      else {
        label.setToolTipText(null);
      }

      return label;
    }

    // Default for Directories
    final JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
    label.setToolTipText(null);
    return label;
  }
}
