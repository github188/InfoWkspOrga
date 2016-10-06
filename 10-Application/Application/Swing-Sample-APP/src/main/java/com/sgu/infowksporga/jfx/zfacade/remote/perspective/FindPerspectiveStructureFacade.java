package com.sgu.infowksporga.jfx.zfacade.remote.perspective;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jidesoft.swing.JideSplitPaneDivider;
import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.rule.ComponentRuleManager;
import com.sgu.core.framework.gui.swing.tree.GTree;
import com.sgu.core.framework.gui.swing.tree.GTreeModel;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.gui.swing.util.UtilNotificationMsg;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.comparator.WorkspaceComparatorOnOrder;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureIn;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureOut;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.tree.node.PerspectiveTreeNode;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.zfacade.AbstractBusinessFacade;
import com.sgu.infowksporga.rest.RestServiceMapping;

/**
 * Description : FindPerspectiveWorkspaceFacade class<br>
 */
@Service
public class FindPerspectiveStructureFacade extends AbstractBusinessFacade<FindPerspectiveStructureOut, PerspectivePanel> {

  /**
   * The logger.
   */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(FindPerspectiveStructureFacade.class);

  /** The Constant comparator. */
  private static final WorkspaceComparatorOnOrder comparator = new WorkspaceComparatorOnOrder();

  /**
   * Save expansion state in case of refresh
   */
  private String expansionState;

  /**
   * Store the selected node
   */
  private TreePath selectedPath;

  /**
   * Constructor<br>
   */
  public FindPerspectiveStructureFacade() {
    super();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void doBeforeServiceCall(final PerspectivePanel container, final ProgressMonitor monitor) {

    expansionState = container.getTree().getExpansionStateAsRowList(0);
    selectedPath = container.getTree().getSelectionPath();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public JComponent getGlasspanesFor(final PerspectivePanel container) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FindPerspectiveStructureOut execute(final PerspectivePanel container) throws TechnicalException, BusinessException {
    FindPerspectiveStructureOut out = null;

    Perspective perspective = container.getCbSelectedPerspective();

    if (perspective != null) {
      final FindPerspectiveStructureIn in = new FindPerspectiveStructureIn();
      in.setUserLogin(GUISession.getInstance().getCurrentUser());
      in.setPerspective(perspective);

      // Call the service
      out = (FindPerspectiveStructureOut) UtilInfoWrkspOrga.callRestBusinessService(RestServiceMapping.URL_SERVICE_FIND_PERSPECTIVE_STRUCTURE,
                                                                                   in, FindPerspectiveStructureOut.class);
    }

    return out;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refreshScreen(final FindPerspectiveStructureOut out, final PerspectivePanel container, final StringBuilder reportMessages,
  final ProgressMonitor monitor) {

    final List<Workspace> workspaces = out.getWorkspaces();
    // sort Workspaces by order
    Collections.sort(workspaces, comparator);

    // Construction de l'arbre contenant les workspaces
    final PerspectiveTreeNode rootNodePerspective = buildPerspectiveTree(workspaces, container);

    // Update tree view
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {

        final GTree tree = container.getTree();
        final GTreeModel model = new GTreeModel(rootNodePerspective);
        tree.setModel(model);

        // Force Refresh current tree model
        ((GTreeModel) container.getTree().getModel()).reload();

        // Re-apply filter if exist
        if (!UtilString.isBlank(container.getTxtFilter().getText())) {
          tree.filterTree(container.getTxtFilter().getText(), container.getChkFilter().isSelected());
        }

        if (expansionState == null) {
          tree.expandAllNode(3);
        }
        else {
          tree.loadExpansionStateAsRowList(0, expansionState);
          UtilNotificationMsg.displayMessage("perspective.reload.completed");
        }

        if (selectedPath != null) {
          tree.setSelectionPath(selectedPath);
        }

        // Modify perspective splitpane color
        if (GUISessionProxy.getInfoWrkspOrgaFrame().getSplitPane().getComponents().length == 2) {
          final JideSplitPaneDivider divider = (JideSplitPaneDivider) GUISessionProxy.getInfoWrkspOrgaFrame().getSplitPane().getComponent(1);
          UtilInfoWrkspOrga.setDividerColor(divider);
        }

        ComponentRuleManager.executeRuleOnAllComponent(GUISessionProxy.getInfoWrkspOrgaFrame());

      }
    });
  }

  /**
   * Builds the perspective tree.
   *
   * @param workspaces the workspaces
   */
  private PerspectiveTreeNode buildPerspectiveTree(final List<Workspace> workspaces, final PerspectivePanel container) {
    int treeNodeIdentifier = 0;
    // build the root Node
    final PerspectiveTreeNode rootNode = new PerspectiveTreeNode(treeNodeIdentifier++, workspaces.get(0));
    final Map<String, PerspectiveTreeNode> parentWkspById = new HashMap<>(workspaces.size());
    parentWkspById.put(rootNode.getWorkspace().getId(), rootNode);

    // build subNodes
    PerspectiveTreeNode parentWorkspaceTreeNode = rootNode;
    for (int i = 1; i < workspaces.size(); i++) {
      final Workspace workspace = workspaces.get(i);
      final PerspectiveTreeNode childNode = new PerspectiveTreeNode(treeNodeIdentifier++, workspaces.get(i));
      parentWkspById.put(childNode.getWorkspace().getId(), childNode);

      if (workspace.getParent() != null) {
        parentWorkspaceTreeNode = parentWkspById.get(workspace.getParent().getId());
        parentWorkspaceTreeNode.add(childNode);
      }

    }

    return rootNode;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void finalizeService(final FindPerspectiveStructureOut out, final PerspectivePanel container, final ProgressMonitor monitor) {

  }

}
