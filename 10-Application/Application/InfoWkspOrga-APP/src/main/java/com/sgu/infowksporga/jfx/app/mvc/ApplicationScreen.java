package com.sgu.infowksporga.jfx.app.mvc;

import com.sgu.core.framework.gui.jfx.screen.AGScreen;
import com.sgu.infowksporga.jfx.perspective.mvc.PerspectivePanelScreen;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class ApplicationScreen.
 */
@Slf4j
@Setter
@Getter
public class ApplicationScreen extends AGScreen<ApplicationModel, ApplicationViewFxml, ApplicationController> {

  /** The perspective screen. */
  private PerspectivePanelScreen perspectiveScreen;

  /**
   * The Constructor.
   */
  public ApplicationScreen() {
    super();
  }

  /**
   * Inits the mvc.
   * Initialized all components by default
   */
  @Override
  public void initMVC() {
    super.initMVC();

    perspectiveScreen = new PerspectivePanelScreen();
    perspectiveScreen.initMVC();

    view().getBrdPnlPerspective().setCenter(perspectiveScreen.getViewRoot());
  }

}
