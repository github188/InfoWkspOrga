package com.sgu.infowksporga.jfx.workspace.dlg.mvc;

import com.sgu.core.framework.gui.jfx.screen.AGController;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The class <strong>WorkspacePanelController</strong>.
 */

@Slf4j
@Setter
@Getter
public final class WorkspaceDlgController extends AGController<WorkspaceDlgModel, WorkspaceDlgViewFxml> {

  /**
   * The Constructor.
   */
  public WorkspaceDlgController() {
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
  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    super.bindComponentsWithPojo();
  }

  /**
   * Call save data
   */
  public void callSaveWokspaceFacade() {
    // final FindDataToInitApplicationFacade facade = SpringBeanHelper.getImplementationByInterface(FindDataToInitApplicationFacade.class);
    //final WorkspacePanelScreen perspectiveScreen = GUISessionProxy.getApplication().getApplicationScreen().getPerspectiveScreen();
    //GUISessionProxy.getGuiSession().getServiceDelegate().execute(facade, perspectiveScreen);
  }

}
