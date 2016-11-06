package com.sgu.infowksporga.jfx.util;

import java.util.List;

import org.dockfx.DockPos;

import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.control.list.AbstractItemVo;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockNode;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.util.UtilDockFX;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTreeItem;
import com.sgu.infowksporga.jfx.perspective.tree.vo.WorkspaceItemVo;

import javafx.scene.control.TreeItem;

/**
 * The Class UtilWorkspace.
 */
public class UtilWorkspace {

  /**
   * The Constructor.
   */
  private UtilWorkspace() {
  }

  /**
   * Adds the dock pane view.
   *
   * @param model the model
   * @param dockPos the dock pos
   */
  public final static void showDefaultDockPane() {
    try {
      // Reinit Dockpane
      GUISessionProxy.getApplication().getApplicationScreen().controller().displayNewEmptyWorkspace();

      final GDockNode dockNode = UtilView.buildDefaultViewDockNode();
      UtilView.addDockNodeView(dockNode, DockPos.RIGHT, null);
      dockNode.getScreen().controller().bindComponentsWithPojo();

    } catch (final Exception e) {
      throw new TechnicalException(e);
    }
  }

  /**
   * Show empty dock pane.
   */
  public static void showEmptyDockPane() {
    // Reinit Dockpane
    GUISessionProxy.getApplication().getApplicationScreen().controller().displayNewEmptyWorkspace();

  }

  /**
   * Builds the xml dock.
   *
   * @return the string
   */
  public static String buildXmlDock() {
    final GDockPane dockPane = GUISessionProxy.getApplication().getApplicationScreen().getView().getDockPane();
    final String xmlDock = UtilDockFX.serializeDockFxStructureToXmlString(dockPane);

    return xmlDock;
  }

  /**
   * Search workspace parent tree item.
   *
   * @param parentTreeItem the parent tree item
   * @param wkspToSearch the wksp to search
   * @param expandAllToWwksp Indicate if we want to deploy the tree view to the new added item
   * @return the perspective tree item
   */
  public static PerspectiveTreeItem searchWorkspaceTreeItem(final PerspectiveTreeItem parentTreeItem, final Workspace wkspToSearch, final boolean expandAllToWksp) {

    final Workspace wkspParent = ((WorkspaceItemVo) parentTreeItem.getValue()).getWorkspace();
    if (wkspParent.equals(wkspToSearch)) {
      if (expandAllToWksp) {
        parentTreeItem.setExpanded(true); // We deploy all the hierarchy to be able to visualize choice node
      }
      return parentTreeItem;
    }

    final List<TreeItem<AbstractItemVo>> children = parentTreeItem.getChildren();
    for (final TreeItem<AbstractItemVo> child : children) {
      final PerspectiveTreeItem pti = searchWorkspaceTreeItem((PerspectiveTreeItem) child, wkspToSearch, expandAllToWksp);
      if (pti != null) {
        if (expandAllToWksp) {
          child.setExpanded(true); // We deploy all the hierarchy to be able to visualize choice node
        }
        return pti;
      }
    }

    return null;
  }

}
