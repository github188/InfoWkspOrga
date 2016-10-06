package com.sgu.infowksporga.jfx.perspective.tree.renderer;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.tree.GTree;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.entity.enumeration.WorkspaceTypeEnum;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.tree.node.PerspectiveTreeNode;
import com.sgu.infowksporga.jfx.perspective.tree.nodevo.WorkspaceNodeVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

/**
 * The Class PerspectiveTreeViewRenderer.
 */
public class PerspectiveTreeCellRenderer extends DefaultTreeCellRenderer {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -4712263397856982288L;

  /** The perspective panel. */
  private PerspectivePanel perspectivePanel;

  /**
   * {@inheritDoc}
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "perspective.filter.apply.tooltip", value = "Un Filtre est appliqué sur la perspective"), // Force \n
                @I18nProperty(key = "perspective.error.loading.tooltip",
                value = "Une erreur s'est produite lors du chargement de la perspective"), // Force \n
                @I18nProperty(key = "perspective.tree.node.root", value = "Racine"), // Force \n
                @I18nProperty(key = "perspective.tree.node.help", value = "Aide"), // Force \n
                @I18nProperty(key = "perspective.tree.node.help.tooltip", value = "Contient le manuel utilisateur"), // Force \n
                @I18nProperty(key = "perspective.tree.node.project", value = "Mes Projets"), // Force \n
                @I18nProperty(key = "perspective.tree.node.tma", value = "Mes TMAs"), // Force \n
  })
  @Override
  public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean sel, final boolean expanded,
  final boolean leaf, final int row, final boolean hasFocus) {
    final JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

    // Re-init tooltip
    label.setToolTipText(null);

    final PerspectiveTreeNode treeNode = (PerspectiveTreeNode) value;
    final WorkspaceNodeVo userObject = (WorkspaceNodeVo) treeNode.getUserObject();

    label.setText(getTitle(userObject.getWorkspace()));

    if (userObject.getWorkspace().getType() == WorkspaceTypeEnum.ROOT) {
      if (perspectivePanel == null) {
        perspectivePanel = (PerspectivePanel) UtilGUI.getParentComponent(tree, PerspectivePanel.class);
      }

      final Perspective perspective = perspectivePanel.getCbSelectedPerspective();
      label.setText("Perspective - " + I18nHelperApp.getMessage(perspective.getName()));

      if (UtilString.isNotBlank(((GTree) tree).getFilteredText())) {
        label.setIcon(UtilGUI.getImageIconFromClasspath("/icons/table/filter.png"));
        label.setToolTipText(I18nHelperApp.getMessage("perspective.filter.apply.tooltip"));
        return label;
      }
      else if (treeNode.getChildCount() == 0) {
        label.setIcon(UtilGUI.getImageIconFromClasspath("/icons/error-loading.png"));
        label.setToolTipText(I18nHelperApp.getMessage("perspective.error.loading.tooltip"));
      }
    }

    String icon = userObject.getWorkspace().getIcon();
    ImageIcon imgIcon = UtilGUI.getImageIcon(icon);
    label.setIcon(imgIcon);

    final String description = getNodeDescription(label, userObject);
    label.setToolTipText(description);

    // Default for Directories
    return label;

  }

  /**
   * Gets the node description.
   *
   * @param label the label
   * @param userObject the user object
   * @return the node description
   */
  private String getNodeDescription(final JLabel label, final WorkspaceNodeVo userObject) {
    if (UtilString.isNotBlank(userObject.getWorkspace().getDescription())) {
      final String description = userObject.getWorkspace().getDescription().replace("${User}",
                                                                                    GUISessionProxy.getGuiSession().getCurrentUser());
      return I18nHelperApp.getMessage(description);
    }

    return I18nHelperApp.getNullMessage(userObject.getWorkspace().getName() + I18NConstant.TOOLTIP_TEXT);
  }

  /**
   * Gets the title.
   *
   * @param workspace the workspace
   * @return the title
   */
  public String getTitle(final Workspace workspace) {
    final String i18nTitle = I18nHelperApp.getMessage(workspace.getName());

    String modifiedTitle = "<HTML>";

    if (workspace.getColor() != null) {
      modifiedTitle += "<font color=\"" + workspace.getColor() + "\"/>";
    }
    if (workspace.isBold() == true) {
      modifiedTitle += "<b>";
    }
    if (workspace.isItalic() == true) {
      modifiedTitle += "<i>";
    }
    if (workspace.isUnderline() == true) {
      modifiedTitle += "<u>";
    }

    if (workspace.isStrike() == true) {
      modifiedTitle += "<strike>";
    }

    modifiedTitle += i18nTitle;

    if (workspace.isStrike() == true) {
      modifiedTitle += "</strike>";
    }
    if (workspace.isUnderline() == true) {
      modifiedTitle += "</u>";
    }
    if (workspace.isItalic() == true) {
      modifiedTitle += "</i>";
    }

    if (workspace.isBold() == true) {
      modifiedTitle += "</b>";
    }

    if (workspace.getColor() != null) {
      modifiedTitle += "</font>";
    }

    modifiedTitle += "</HTML>";

    return modifiedTitle;
  }
}
