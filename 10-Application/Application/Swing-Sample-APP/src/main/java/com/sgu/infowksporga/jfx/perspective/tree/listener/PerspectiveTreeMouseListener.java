package com.sgu.infowksporga.jfx.perspective.tree.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import com.sgu.core.framework.gui.swing.tree.GTree;

/**
 * Description : Display Popup menu item<br>
 */
public class PerspectiveTreeMouseListener extends MouseAdapter {
  /**
   * Reference to the tree
   */
  private final GTree tree;

  /**
   * Store ref popup menu
   */
  private JPopupMenu popupMenu;

  /**
   * Constructor<br>
   *
   * @param tree Reference to the tree
   */
  public PerspectiveTreeMouseListener(final GTree tree) {
    super();
    this.tree = tree;
    initPopupMenu();
  }

  /**
   * Description : initPopupMenu method <br>
   */
  /**
   * Description : initPopupMenu method <br>
   */
  private void initPopupMenu() {
    popupMenu = new JPopupMenu();

    //final ExpandAllPerspectiveAction expandPerspective = new ExpandAllPerspectiveAction(tree);
    //final CollapseAllPerspectiveAction collapsePerspective = new CollapseAllPerspectiveAction(tree);

    //popupMenu.add(collapsePerspective.createMenuItem(true, true));
    //popupMenu.add(expandPerspective.createMenuItem(true, true));

    popupMenu.setOpaque(true);
    popupMenu.setLightWeightPopupEnabled(true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void mousePressed(final MouseEvent e) {
    final int selRow = tree.getRowForLocation(e.getX(), e.getY());

    if (selRow != -1 && e.getClickCount() == 1 && SwingUtilities.isRightMouseButton(e)) {
      popupMenu.show((JComponent) e.getSource(), e.getX(), e.getY());
    }
  }
}
