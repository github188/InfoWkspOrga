package com.sgu.infowksporga.jfx.perspective.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.tree.GTreeModel;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTree;

/**
 * The Class ClearFilterAction.
 */
public class ClearFilterAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The reference to get the directory tree
   */
  private final PerspectivePanel perspectivePanel;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "perspective.action.clear.filter.text", value = "Efface le filtre"), // Force \n
                @I18nProperty(key = "perspective.action.clear.filter.tooltip",
                value = "Efface le filtre et ré-affiche l'ensemble des workspaces de la perspective "), // Force \n
                @I18nProperty(key = "perspective.action.clear.filter.icon", value = "/icons/table/clear-filter.png"), // Force \n
  })
  public ClearFilterAction(final PerspectivePanel perspectivePanel) {
    super("perspective.action.clear.filter");
    this.perspectivePanel = perspectivePanel;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evts) {
    perspectivePanel.getTxtFilter().setText("");

    final boolean onLeaf = perspectivePanel.getChkFilter().isSelected();
    PerspectiveTree tree = perspectivePanel.getTree();
    tree.filterTree("", onLeaf);
    ((GTreeModel) tree.getModel()).reload();
    tree.expandAllNode(1);
  }
}