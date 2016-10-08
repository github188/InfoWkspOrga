package com.sgu.infowksporga.jfx.main.ui;

import com.sgu.core.framework.gui.jfx.screen.AGScreen;

import javafx.scene.Scene;

/**
 * The Class ApplicationScreen.
 */
public class ApplicationScreen extends AGScreen<Scene, ApplicationModel, ApplicationViewFxml, ApplicationController> {

  /**
   * The Constructor.
   */
  public ApplicationScreen() {
    this(null);
  }

  /**
   * The Constructor.
   *
   * @param scene the scene
   */
  public ApplicationScreen(final Scene scene) {
    super(scene);
  }

  /**
   * Inits the mvc.
   * Initialized all components by default
   */
  @Override
  protected void initMVC() {
    super.initMVC();
  }

}
