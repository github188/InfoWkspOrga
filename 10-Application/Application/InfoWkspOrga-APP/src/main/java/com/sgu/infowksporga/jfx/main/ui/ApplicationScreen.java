package com.sgu.infowksporga.jfx.main.ui;

import com.sgu.core.framework.gui.jfx.screen.AGScreen;
import com.sgu.infowksporga.jfx.perspective.PerspectiveScreen;

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
  private PerspectiveScreen perspectiveScreen;

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

    perspectiveScreen = new PerspectiveScreen();
    perspectiveScreen.initMVC();

    view().getBrdPnlPerspective().setCenter(perspectiveScreen.getViewRoot());
  }

}
