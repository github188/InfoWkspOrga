package com.sgu.infowksporga.jfx.perspective;

import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.perspective.action.ClearFilterAction;
import com.sgu.infowksporga.jfx.perspective.action.CollapseAllPerspectiveAction;
import com.sgu.infowksporga.jfx.perspective.action.ExpandAllPerspectiveAction;
import com.sgu.infowksporga.jfx.perspective.action.RefreshPerspectiveAction;
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
public final class PerspectiveController extends AGController<PerspectiveModel, PerspectiveViewFxml> {

  /**
   * The Constructor.
   */
  public PerspectiveController() {
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
    new ClearFilterAction(view().getBtnClearWorkspaceFilter());
    view().getBtnClearWorkspaceFilter().setText("");

    new CollapseAllPerspectiveAction(view().getBtnCollapseWorkspaceTree());
    view().getBtnCollapseWorkspaceTree().setText("");

    new ExpandAllPerspectiveAction(view().getBtnExpandWorkspaceTree());
    view().getBtnExpandWorkspaceTree().setText("");

    new RefreshPerspectiveAction(view().getBtnRefreshPerspective());
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
    final PerspectiveScreen perspectiveScreen = GUISessionProxy.getCurrentApplication().getApplicationScreen().getPerspectiveScreen();
    GUISessionProxy.getGuiSession().getServiceDelegate().execute(facade, perspectiveScreen);
  }

}
