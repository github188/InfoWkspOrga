package com.sgu.infowksporga.jfx.action;

import com.sgu.core.framework.gui.jfx.action.AbstractAction;
import com.sgu.core.framework.i18n.I18nHelper;

import javafx.event.Event;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class App Base Action.
 */
@Slf4j
public abstract class AppBaseAction<T extends Event> extends AbstractAction<T> {

  /**
   * The Constructor.
   *
   * @param control The control
   * @param bundleConfigKey The bundle config key
   */
  public AppBaseAction(final Control control, final String bundleConfigKey, final I18nHelper i18nHelper) {
    super(control, bundleConfigKey, i18nHelper);
  }

}
