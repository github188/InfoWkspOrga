package com.sgu.infowksporga.jfx.rules.workspace;

import com.sgu.core.framework.gui.swing.rule.AbstractComponentRule;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

/**
 * Description : IsWorkspaceSelectedRule class<br>
 */
public class IsWorkspaceSelectedRule extends AbstractComponentRule {

  /**
   * {@inheritDoc}
   */
  @Override
  public void executeRule() {
    // Get the current Perspective selected node
    final PerspectivePanel perspective = (PerspectivePanel) GUISessionProxy.getCurrentPerspective();
    final Object userObject = perspective.getTree().getLastSelectedUserObject();

    // Get current workspace
    if (GUISessionProxy.getCurrentWorkspace() != null) {
      if (userObject != null) {
        getTargetComponent().setEnabled(true);
      }
      else {
        getTargetComponent().setEnabled(false);
      }
    }
    else {
      getTargetComponent().setEnabled(false);
    }
  }

}
