package com.sgu.infowksporga.jfx.perspective;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.infowksporga.business.comparator.WorkspaceComparatorOnOrder;
import com.sgu.infowksporga.business.dto.WorkspaceDto;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveOut;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureOut;
import com.sgu.infowksporga.jfx.perspective.cb.CbbPerspectiveItem;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTreeItem;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class PerspectiveModel.
 */
@Slf4j
@Setter
@Getter
public class PerspectiveModel extends AGModel<PerspectiveViewFxml, PerspectiveController> {

  /** The Constant comparator. */
  private static final WorkspaceComparatorOnOrder comparator = new WorkspaceComparatorOnOrder();

  /**
   * The Constructor.
   */
  public PerspectiveModel() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();

    controller().callFindDataToInitApplicationFacade();
  }

  /**
   * Fill ui combo box
   *
   * @param perspectives the perspectives
   */
  public void fillUI(final FindPerspectiveOut out) {

    view().getCbbPerspective().getItems().clear();

    final List<Perspective> perspectives = out.getPerspectiveLst();
    for (final Perspective perspective : perspectives) {
      final CbbPerspectiveItem vo = new CbbPerspectiveItem(perspective);
      view().getCbbPerspective().getItems().add(vo);
    }

    if (perspectives.size() == 1) {
      view().getCbbPerspective().getSelectionModel().select(0);
    }

  }

  /**
   * Fill ui perpective tree view
   *
   * @param perspectives the perspectives
   */
  public void fillUI(final FindPerspectiveStructureOut out) {

    final List<Workspace> workspaces = out.getWorkspaces();
    // sort Workspaces by order
    Collections.sort(workspaces, comparator);

    // Construction de l'arbre contenant les workspaces
    final PerspectiveTreeItem rootItemPerspective = buildPerspectiveTree(workspaces);

    view().getTreeWorkspaces().setRoot(rootItemPerspective);

  }

  /**
   * Builds the perspective tree.
   *
   * @param workspaces the workspaces
   */
  private PerspectiveTreeItem buildPerspectiveTree(final List<Workspace> workspaces) {
    int treeNodeIdentifier = 0;
    // build the root Node
    final PerspectiveTreeItem rootItem = new PerspectiveTreeItem(treeNodeIdentifier++, new WorkspaceDto(workspaces.get(0)));
    final Map<String, PerspectiveTreeItem> parentWkspById = new HashMap<>(workspaces.size());
    parentWkspById.put(rootItem.getWorkspace().getId(), rootItem);

    // build subNodes
    PerspectiveTreeItem parentWorkspaceTreeItem = rootItem;
    for (int i = 1; i < workspaces.size(); i++) {
      final Workspace workspace = workspaces.get(i);
      final PerspectiveTreeItem childNode = new PerspectiveTreeItem(treeNodeIdentifier++, new WorkspaceDto(workspaces.get(i)));
      parentWkspById.put(childNode.getWorkspace().getId(), childNode);

      if (workspace.getParent() != null) {
        parentWorkspaceTreeItem = parentWkspById.get(workspace.getParent().getId());
        parentWorkspaceTreeItem.getChildren().add(childNode);
      }

    }

    return rootItem;

  }
}
