package com.sgu.infowksporga.jfx.perspective.action;

import java.awt.event.ActionEvent;

import javax.swing.tree.TreePath;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.tree.GTree;
import com.sgu.core.framework.gui.swing.tree.GTreeModel;
import com.sgu.core.framework.gui.swing.tree.GTreeNode;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;

/**
 * Description : CollapseAllPerspectiveAction class<br>
 * 
 * @author SGU
 */
public class CollapseAllPerspectiveAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The reference to get the directory tree
   */
  private PerspectivePanel perspectivePanel;

  /**
   * the ref to tree
   */
  private GTree tree;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "perspective.action.collapse.text", value = "Plier tous les noeuds"), // Force \n
                @I18nProperty(key = "perspective.action.collapse.tooltip", value = "Plier tous les noeuds"), // Force \n
                @I18nProperty(key = "perspective.action.collapse.icon", value = "/icons/collapse.png"), // Force \n
  })
  public CollapseAllPerspectiveAction(final GTree tree) {
    super("perspective.action.collapse");
    this.tree = tree;
  }

  /**
   * Constructor<br>
   */
  public CollapseAllPerspectiveAction(final PerspectivePanel perspectivePanel) {
    this((GTree) null);
    this.perspectivePanel = perspectivePanel;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    if (tree == null) {
      tree = perspectivePanel.getTree();
    }

    if (tree != null) {
      GTreeNode node = (GTreeNode) tree.getLastSelectedPathComponent();
      if (node == null) {
        node = (GTreeNode) tree.getModel().getRoot();
      }

      if (node.equals(tree.getModel().getRoot())) {
        ((GTreeModel) tree.getModel()).reload();
        tree.expandAllNode(1);
      }
      else {
        tree.collapseAll(new TreePath(node), 1);
      }
    }

  }
}