package com.sgu.infowksporga.jfx.perspective.mvc;

import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.perspective.action.ClearWorkspacesFilterAction;
import com.sgu.infowksporga.jfx.perspective.action.CollapseAllWorkspacesAction;
import com.sgu.infowksporga.jfx.perspective.action.ExpandAllWorkspacesAction;
import com.sgu.infowksporga.jfx.perspective.action.RefreshPerspectivePanelAction;
import com.sgu.infowksporga.jfx.perspective.tree.TreeCellChangeListener;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.y_service.remote.FindDataToInitApplicationFacade;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The class <strong>PerspectiveController</strong>.
 */

@Slf4j
@Setter
@Getter
public final class PerspectivePanelController extends AGController<PerspectivePanelModel, PerspectivePanelViewFxml> {

  /**
   * The Constructor.
   */
  public PerspectivePanelController() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createListeners() {
    super.createListeners();

    //---------------------------------------------------
    // initialize Actions
    // set empty String for toolbar buttons
    //---------------------------------------------------
    new ClearWorkspacesFilterAction(view().getBtnClearWorkspaceFilter());
    view().getBtnClearWorkspaceFilter().setText("");

    new CollapseAllWorkspacesAction(view().getBtnCollapseWorkspaceTree());
    view().getBtnCollapseWorkspaceTree().setText("");

    new ExpandAllWorkspacesAction(view().getBtnExpandWorkspaceTree());
    view().getBtnExpandWorkspaceTree().setText("");

    new RefreshPerspectivePanelAction(view().getBtnRefreshPerspective());
    view().getBtnRefreshPerspective().setText("");

    view().getTreeWorkspaces().getSelectionModel().selectedItemProperty().addListener(new TreeCellChangeListener());

  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    super.bindComponentsWithPojo();
  }

  /**
   * Call find data to init application facade.
   */
  public void callFindDataToInitApplicationFacade() {
    final FindDataToInitApplicationFacade facade = SpringBeanHelper.getImplementationByInterface(FindDataToInitApplicationFacade.class);
    final PerspectivePanelScreen perspectiveScreen = GUISessionProxy.getApplication().getApplicationScreen().getPerspectiveScreen();
    GUISessionProxy.getGuiSession().getServiceDelegate().execute(facade, perspectiveScreen);
  }

  /**
   * Refresh perspective panel.
   */
  public void refreshPerspectivePanel() {
    callFindDataToInitApplicationFacade();
  }

  /**
   * Clear workspaces filter.
   */
  public void clearWorkspacesFilter() {

  }

  /**
   * Collapse all workspaces.
   */
  public void collapseAllWorkspaces() {
    final PerspectivePanelScreen perspectiveScreen = GUISessionProxy.getApplication().getApplicationScreen().getPerspectiveScreen();
    perspectiveScreen.getView().getTreeWorkspaces().collapseAll();
    perspectiveScreen.getView().getTreeWorkspaces().expandAll(0);

  }

  /**
   * Expand all workspaces.
   */
  public void expandAllWorkspaces() {
    final PerspectivePanelScreen perspectiveScreen = GUISessionProxy.getApplication().getApplicationScreen().getPerspectiveScreen();
    perspectiveScreen.getView().getTreeWorkspaces().expandAll();
  }

}
