package com.sgu.infowksporga.jfx.zfacade.local.edit;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.rule.ComponentRuleManager;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.zfacade.local.AbstractInfoWrkspOrgaServiceUI;

import lombok.Getter;
import lombok.Setter;
import net.infonode.docking.internalutil.InternalDockingUtil;

/**
 * Description : Unselect All Nodes For All Directory Desk View Facade class<br>
 */
@Service
@Getter
@Setter
public class UnselectAllNodesForAllFileViewServiceUI extends AbstractInfoWrkspOrgaServiceUI {

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(UnselectAllNodesForAllFileViewServiceUI.class);

  /**
   * Constructor<br>
   */
  public UnselectAllNodesForAllFileViewServiceUI() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(final Object... params) throws TechnicalException, BusinessException {
    final FileExplorerView currentView = (FileExplorerView) params[0];

    final ArrayList<Object> views = new ArrayList<Object>(10);

    // If the current view is null search on all Workspace views
    if (currentView == null) {
      InternalDockingUtil.getViews(GUISessionProxy.getCurrentWorkspace().getRootWindow(), views);
    }
    else {
      views.add(currentView);
    }

    GUISessionProxy.setLastSelectedFile(null);

    for (final Object view : views) {
      if (view instanceof FileExplorerView) {
        final FileExplorerView directoryView = (FileExplorerView) view;
        // Clear selection
        directoryView.getFileTree().getSelectionModel().clearSelection();
      }
    }

    // Rebuild rules
    ComponentRuleManager.executeRuleOnAllComponent(GUISession.getInstance().getApplicationFrame());

    return null;
  }

}
