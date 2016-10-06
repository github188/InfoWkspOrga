package com.sgu.infowksporga.jfx.perspective.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.tree.GTree;
import com.sgu.core.framework.gui.swing.tree.GTreeNode;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;

/**
 * Description : ExpandAllPerspectiveAction class<br>
 * 
 * @author SGU
 */
public class ExpandAllPerspectiveAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Store the reference of perspective Panel
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
                @I18nProperty(key = "perspective.action.expand.text", value = "Ouvrir tous les noeuds"), // Force \n
                @I18nProperty(key = "perspective.action.expand.tooltip", value = "Ouvrir tous les noeuds"), // Force \n
                @I18nProperty(key = "perspective.action.expand.icon", value = "/icons/expand.png"), // Force \n
  })
  public ExpandAllPerspectiveAction(final GTree tree) {
    super("perspective.action.expand");
    this.tree = tree;
  }

  /**
   * Constructor<br>
   */
  public ExpandAllPerspectiveAction(final PerspectivePanel perspectivePanel) {
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

      tree.expandAllNode(GTree.getPath(node), -1);
    }
  }

}