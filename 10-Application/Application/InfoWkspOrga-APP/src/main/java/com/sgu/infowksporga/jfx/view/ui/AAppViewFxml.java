package com.sgu.infowksporga.jfx.view.ui;

import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewFxml;
import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewScreen;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class AAppViewFxml.
 */
@Slf4j
@Setter
@Getter
public abstract class AAppViewFxml<S extends ADockableViewScreen, M extends AAppViewModel, C extends AAppViewController> extends ADockableViewFxml<S, M, C> {

  /**
   * The Constructor.
   */
  public AAppViewFxml() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createUI() {
    super.createUI();
  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();
  }

}
