package com.sgu.infowksporga.jfx.perspective.tree.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.core.framework.gui.swing.tree.GTree;
import com.sgu.core.framework.gui.swing.tree.GTreeModel;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;

/**
 * Description : PerspectiveTreeFilterKeyListener class<br>
 */
public class PerspectiveTreeFilterListener implements ActionListener {

  /**
   * Ref to the tree to filter on
   */
  private final PerspectivePanel perspectivePanel;

  /**
   * Constructor<br>
   */
  public PerspectiveTreeFilterListener(final PerspectivePanel perspectivePanel) {
    this.perspectivePanel = perspectivePanel;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    final boolean onLeaf = perspectivePanel.getChkFilter().isSelected();

    final GTree tree = perspectivePanel.getTree();

    final GTextField textField = perspectivePanel.getTxtFilter();
    final String filterTxt = textField.getText();
    if (UtilString.isNotBlank(filterTxt)) {
      tree.filterTree(filterTxt, onLeaf);
      tree.expandAllNode();
    }
    else {
      tree.filterTree("", onLeaf);
      ((GTreeModel) tree.getModel()).reload();
      tree.expandAllNode(1);
    }

  }

}
