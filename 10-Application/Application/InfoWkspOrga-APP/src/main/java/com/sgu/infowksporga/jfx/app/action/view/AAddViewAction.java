package com.sgu.infowksporga.jfx.app.action.view;

import com.sgu.core.framework.i18n.I18nHelper;
import com.sgu.infowksporga.jfx.action.AppBaseAction;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : AViewAddAction class<br>
 */
@Slf4j

public class AAddViewAction extends AppBaseAction<ActionEvent> {

  /**
   * The Constructor.
   *
   * @param control the control
   * @param bundleConfigKey the bundle config key
   * @param i18nHelper the i18n helper
   */
  public AAddViewAction(final Control control, final String bundleConfigKey, final I18nHelper i18nHelper) {
    super(control, bundleConfigKey, i18nHelper);
  }

  /**
   * Adds the view.
   * Use for dev testing
   *
   * @param model the model
   */
  /*
   * protected void addDefaultView2(final ADockableViewModel model) {
   * try {
   * final String xmlStructure = UtilIO.readFile(
   * "G:\\Projects\\400-InfoWkspOrga\\10-Application\\Application\\InfoWkspOrga-APP\\src\\main\\java\\com\\sgu\\infowksporga\\jfx\\main\\action\\view\\workspace.xml"
   * );
   * final GDockPane dockPaneFromXml = UtilDockFX.deSerializeDockFxStructure(xmlStructure);
   * GUISessionProxy.getApplication().getApplicationScreen().getView().getPnlWorkspace().setCenter(dockPaneFromXml);
   * GUISessionProxy.getApplication().getApplicationScreen().getView().setDockPane(dockPaneFromXml);
   * } catch (final Exception e) {
   * throw new TechnicalException(e);
   * }
   * }
   */

}
