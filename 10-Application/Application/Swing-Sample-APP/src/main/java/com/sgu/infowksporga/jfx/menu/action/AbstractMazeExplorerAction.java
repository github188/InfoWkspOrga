package com.sgu.infowksporga.jfx.menu.action;

import com.sgu.core.framework.gui.swing.action.AbstractGAction;
import com.sgu.core.framework.i18n.I18nHelper;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

/**
 * Description : AbstractInfoWrkspOrgaAction class<br>
 *
 * @author SGU
 */
public abstract class AbstractInfoWrkspOrgaAction extends AbstractGAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -4017773186618015427L;

  public AbstractInfoWrkspOrgaAction(String bundleConfigKey, I18nHelper i18nHelper) {
    super(bundleConfigKey, i18nHelper);
  }

  public AbstractInfoWrkspOrgaAction(String bundleConfigKey) {
    super(bundleConfigKey, I18nHelperApp.getI18nHelper());
  }

}
