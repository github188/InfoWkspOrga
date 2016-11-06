package com.sgu.infowksporga.jfx.perspective.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveOut;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureOut;
import com.sgu.infowksporga.jfx.perspective.cbb.CbbPerspectiveItemVo;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTreeItem;

import javafx.application.Platform;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class PerspectiveModel.
 */
@Slf4j
@Setter
@Getter
public class PerspectivePanelModel extends AGModel<PerspectivePanelViewFxml, PerspectivePanelController> {

  /**
   * The Constructor.
   */
  public PerspectivePanelModel() {
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
    // First store cbbPerspective selected item (in case of refresh of the perspective panel)
    final CbbPerspectiveItemVo selectedPerspective = (CbbPerspectiveItemVo) view().getCbbPerspective().getSelectionModel().getSelectedItem();

    view().getCbbPerspective().getItems().clear();

    final List<Perspective> perspectives = out.getPerspectiveLst();
    for (final Perspective perspective : perspectives) {
      final CbbPerspectiveItemVo vo = new CbbPerspectiveItemVo(perspective);
      view().getCbbPerspective().getItems().add(vo);
    }

    if (perspectives.size() == 1) {
      Platform.runLater(() -> {
        view().getCbbPerspective().getSelectionModel().select(0);
      });

    }
    else if (selectedPerspective != null) {
      view().getCbbPerspective().getSelectionModel().select(selectedPerspective);
    }

  }

  /**
   * Fill ui perpective tree view
   *
   * @param perspectives the perspectives
   */
  public void fillUI(final FindPerspectiveStructureOut out) {
    // Store the original workspace order
    Platform.runLater(() -> {
      final CbbPerspectiveItemVo selectedPerspective = (CbbPerspectiveItemVo) view().getCbbPerspective().getSelectionModel().getSelectedItem();
      selectedPerspective.setCurrentWorkspaceIdOrder(out.getCurrentWorkspaceIdOrder());
    });

    // Store cbbPerspective selected item (in case of refresh of the perspective panel)
    final PerspectiveTreeItem selectedWksp = (PerspectiveTreeItem) view().getTreeWorkspaces().getSelectionModel().getSelectedItem();

    final List<Workspace> workspaces = out.getWorkspaces();

    // Construction de l'arbre contenant les workspaces
    final PerspectiveTreeItem rootItemPerspective = buildPerspectiveTree(workspaces);

    view().getTreeWorkspaces().setRoot(null);
    view().getTreeWorkspaces().setRoot(rootItemPerspective);

    if (selectedWksp != null) {
      view().getTreeWorkspaces().getSelectionModel().select(selectedWksp);
    }

  }

  /**
   * Builds the perspective tree.
   *
   * @param workspaces the workspaces
   */
  private PerspectiveTreeItem buildPerspectiveTree(final List<Workspace> workspaces) {
    // build the root Node
    final PerspectiveTreeItem rootItem = new PerspectiveTreeItem(workspaces.get(0));
    final Map<String, PerspectiveTreeItem> parentWkspById = new HashMap<>(workspaces.size());
    parentWkspById.put(rootItem.getWorkspace().getId(), rootItem);

    // build subNodes
    PerspectiveTreeItem parentWorkspaceTreeItem = rootItem;
    for (int i = 1; i < workspaces.size(); i++) {
      final Workspace workspace = workspaces.get(i);
      final PerspectiveTreeItem childNode = new PerspectiveTreeItem(workspaces.get(i));
      parentWkspById.put(childNode.getWorkspace().getId(), childNode);

      if (workspace.getParent() != null) {
        parentWorkspaceTreeItem = parentWkspById.get(workspace.getParent().getId());
        parentWorkspaceTreeItem.getChildren().add(childNode);
      }

    }

    return rootItem;

  }
}
