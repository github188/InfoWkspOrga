package com.sgu.infowksporga.jfx.main.action.view;

import org.dockfx.DockPos;

import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockNode;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewModel;
import com.sgu.core.framework.i18n.I18nHelper;
import com.sgu.core.framework.util.UtilDockFX;
import com.sgu.infowksporga.jfx.action.AppBaseAction;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : AViewAddAction class<br>
 */
@Slf4j

public class AViewAddAction extends AppBaseAction<ActionEvent> {

  /**
   * The Constructor.
   *
   * @param control the control
   * @param bundleConfigKey the bundle config key
   * @param i18nHelper the i18n helper
   */
  public AViewAddAction(final Control control, final String bundleConfigKey, final I18nHelper i18nHelper) {
    super(control, bundleConfigKey, i18nHelper);
  }

  /**
   * Adds the view.
   *
   * @param model the model
   */
  protected void addView(ADockableViewModel model) {
    try {
      final GDockNode dockNode = UtilDockFX.buildDockableViewNodeFromModel(model);
      final GDockPane dockPane = GUISessionProxy.getCurrentApplication().getApplicationScreen().getView().getDockPane();
      dockNode.dock(dockPane, DockPos.RIGHT);
    } catch (Exception e) {
      throw new TechnicalException(e);
    }
  }

}