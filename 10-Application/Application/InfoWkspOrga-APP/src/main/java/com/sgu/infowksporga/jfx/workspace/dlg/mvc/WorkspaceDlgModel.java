package com.sgu.infowksporga.jfx.workspace.dlg.mvc;

import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.infowksporga.business.comparator.WorkspaceComparatorOnOrder;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveOut;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureOut;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class WorkspacePanelModel.
 */
@Slf4j
@Setter
@Getter
public class WorkspaceDlgModel extends AGModel<WorkspaceDlgViewFxml, WorkspaceDlgController> {

  /** The Constant comparator. */
  private static final WorkspaceComparatorOnOrder comparator = new WorkspaceComparatorOnOrder();

  /**
   * The Constructor.
   */
  public WorkspaceDlgModel() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();

    //controller().callFindDataToInitApplicationFacade();
  }

  /**
   * Fill ui combo box
   *
   * @param perspectives the perspectives
   */
  public void fillUI(final FindPerspectiveOut out) {

    /*
     * view().getCbbPerspective().getItems().clear();
     * final List<Perspective> perspectives = out.getPerspectiveLst();
     * for (final Perspective perspective : perspectives) {
     * final CbbPerspectiveItem vo = new CbbPerspectiveItem(perspective);
     * view().getCbbPerspective().getItems().add(vo);
     * }
     * if (perspectives.size() == 1) {
     * view().getCbbPerspective().getSelectionModel().select(0);
     * }
     */

  }

  /**
   * Fill ui perpective tree view
   *
   * @param perspectives the perspectives
   */
  public void fillUI(final FindPerspectiveStructureOut out) {

    /*
     * final List<Workspace> workspaces = out.getWorkspaces();
     * // sort Workspaces by order
     * Collections.sort(workspaces, comparator);
     * // Construction de l'arbre contenant les workspaces
     * final PerspectiveTreeItem rootItemPerspective = buildPerspectiveTree(workspaces);
     * view().getTreeWorkspaces().setRoot(rootItemPerspective);
     */

  }

}
