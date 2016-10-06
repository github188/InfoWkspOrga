package com.sgu.infowksporga.jfx.rules.workspace;

import com.sgu.core.framework.gui.swing.rule.AbstractComponentRule;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.tree.nodevo.WorkspaceNodeVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

/**
 * Description : IsWorkspaceSaveAvailableRule class<br>
 */
public class IsWorkspaceSaveAvailableRule extends AbstractComponentRule {

  /**
   * {@inheritDoc}
   */
  @Override
  public void executeRule() {
    // Get the current Perspective selected node
    final PerspectivePanel perspective = (PerspectivePanel) GUISessionProxy.getCurrentPerspective();
    final Object userObject = perspective.getTree().getLastSelectedUserObject();

    // Put in Status bar Workspace information
    if (userObject instanceof WorkspaceNodeVo) {
      getTargetComponent().setEnabled(true);
    }
    else {
      getTargetComponent().setEnabled(false);
    }

  }

}
