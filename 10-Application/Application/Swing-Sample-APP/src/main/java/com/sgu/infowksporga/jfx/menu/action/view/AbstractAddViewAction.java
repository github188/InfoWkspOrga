package com.sgu.infowksporga.jfx.menu.action.view;

import com.sgu.infowksporga.business.dto.WorkspaceDto;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.rules.workspace.IsWorkspaceSelectedRule;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

/**
 * Description : AbstractAddViewAction class<br>
 *
 * @author SGU
 */
public abstract class AbstractAddViewAction extends AbstractInfoWrkspOrgaAction {

  public AbstractAddViewAction(String bundleConfigKey) {
    super(bundleConfigKey);
    setRule(new IsWorkspaceSelectedRule());
  }

  /**
   * Gets the workspaces views dto.
   *
   * @return the workspaces views dto
   */
  public WorkspaceDto getWorkspaceDto() {
    return GUISessionProxy.getCurrentWorkspace().getWorkspaceDto();
  }

}
