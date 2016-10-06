package com.sgu.infowksporga.jfx.perspective.tree;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ToolTipManager;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.sgu.core.framework.gui.swing.tree.GTree;
import com.sgu.core.framework.gui.swing.tree.GTreeModel;
import com.sgu.infowksporga.business.dto.PerspectiveWorkspaceOrderDto;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.jfx.perspective.AbstractPerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.tree.listener.PerspectiveTreeMouseListener;
import com.sgu.infowksporga.jfx.perspective.tree.listener.PerspectiveTreeSelectionListener;
import com.sgu.infowksporga.jfx.perspective.tree.node.PerspectiveTreeNode;
import com.sgu.infowksporga.jfx.perspective.tree.renderer.PerspectiveTreeCellRenderer;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.util.OrderManager;

/**
 * Description : PerspectiveTree class<br>
 */
public class PerspectiveTree extends GTree {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -7993259403150638286L;

  /**
   * The tree renderer
   */
  protected static final DefaultTreeCellRenderer TREE_RENDERER = new PerspectiveTreeCellRenderer();

  /**
   * 
   */
  private PerspectiveTreeSelectionListener perspectiveTreeSelectionListener;

  /**
   * Constructor<br>
   *
   * @param treeModel The tree content
   */
  public PerspectiveTree(final GTreeModel treeModel) {
    super(treeModel);
    initializeTree();
  }

  /**
   * Description : initializeTree method <br>
   */
  protected void initializeTree() {
    // Register the tree with the ToolTipManager (which isn’t necessary for nearly all other Swing Components…)
    ToolTipManager.sharedInstance().registerComponent(this);

    setShowsRootHandles(false);
    setCellRenderer(TREE_RENDERER);

    getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    setBorder(new EmptyBorder(0, 0, 0, 0));

    // Add mouse listener to display related properties
    perspectiveTreeSelectionListener = new PerspectiveTreeSelectionListener(this);
    getSelectionModel().addTreeSelectionListener(perspectiveTreeSelectionListener);

    // Add mouse listener to show popup menu
    addMouseListener(new PerspectiveTreeMouseListener(this));

  }

  /**
   * @see #perspectiveTreeSelectionListener
   * @return the perspectiveTreeSelectionListener : See field description
   */
  public PerspectiveTreeSelectionListener getPerspectiveTreeSelectionListener() {
    return perspectiveTreeSelectionListener;
  }

  /**
   * Search tree node recursively.
   *
   * @param node the node
   * @param workspaceId the workspace id
   * @return the perspective tree node
   */
  public PerspectiveTreeNode searchTreeNodeRecursively(PerspectiveTreeNode node, String workspaceId) {
    PerspectiveTreeNode result = null;

    if (workspaceId.equals(node.getWorkspace().getId())) {
      result = node;
    }
    else {

      if (node.getChildCount() != 0) {
        for (int i = 0; i < node.getChildCount(); i++) {
          PerspectiveTreeNode child = (PerspectiveTreeNode) node.getChildAt(i);
          result = searchTreeNodeRecursively(child, workspaceId);
          if (result != null) {
            break;
          }
        }
      }
    }

    return result;
  }

  /**
   * Re index perspective workspace order.
   *
   * @return the map< string, integer> --> <WorkspaceId, newOrder>
   */
  public PerspectiveWorkspaceOrderDto reIndexPerspectiveWorkspaceOrder() {
    PerspectiveWorkspaceOrderDto pwo = new PerspectiveWorkspaceOrderDto();

    AbstractPerspectivePanel pnlPerspective = GUISessionProxy.getCurrentPerspective();
    Perspective perspective = pnlPerspective.getCbSelectedPerspective();
    pwo.setPerspectiveId(perspective.getId());

    Map<String, Integer> newWorkspaceIdOrder = new HashMap<>();

    PerspectiveTree tree = pnlPerspective.getTree();
    TreeModel model = tree.getModel();
    PerspectiveTreeNode root = (PerspectiveTreeNode) model.getRoot();
    final OrderManager startWorkspaceOrder = new OrderManager();
    startWorkspaceOrder.nextOrder = 1;
    defineWorkspaceOrderRecursively(root, startWorkspaceOrder, newWorkspaceIdOrder);

    pwo.setNewWorkspaceIdOrder(newWorkspaceIdOrder);

    return pwo;
  }

  /**
   * Define workspace order recursively.
   *
   * @param parent the parent
   * @param orderManager the order manager
   * @param newWorkspaceIdOrder the new workspace id order
   */
  private void defineWorkspaceOrderRecursively(PerspectiveTreeNode parent, OrderManager orderManager,
  Map<String, Integer> newWorkspaceIdOrder) {
    newWorkspaceIdOrder.put(parent.getWorkspace().getId(), orderManager.nextOrder++);

    if (parent.getChildCount() != 0) {
      for (int i = 0; i < parent.getChildCount(); i++) {
        PerspectiveTreeNode child = (PerspectiveTreeNode) parent.getChildAt(i);
        defineWorkspaceOrderRecursively(child, orderManager, newWorkspaceIdOrder);
      }
    }
  }

}
