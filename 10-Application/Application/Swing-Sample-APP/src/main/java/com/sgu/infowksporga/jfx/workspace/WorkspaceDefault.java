package com.sgu.infowksporga.jfx.workspace;

import com.sgu.infowksporga.business.dto.WorkspaceDto;
import com.sgu.infowksporga.jfx.views.ViewDefault;

import net.infonode.docking.util.ViewMap;

/**
 * Description : WorkspaceDefault class<br>
 */
public class WorkspaceDefault extends AbstractWorkspace {

  /**
   * Constant used to identified Workspace Default
   */
  public static final String WORKSPACE_DEFAULT = "WORKSPACE_DEFAULT";

  /**
   * ref to the default view
   */
  private ViewDefault viewDefault;

  /**
   * Constructor<br>
   * 
   * @param configuration the workspace configuration come from DB
   */
  public WorkspaceDefault(final WorkspaceDto configuration) {
    super(configuration);
  }

  /**
   * Constructor<br>
   */
  public WorkspaceDefault() {
    super(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void defineWorkspaceLayout() {
    viewDefault = new ViewDefault();
    rootWindow.setWindow(viewDefault);
  }

  /**
   * @see #viewDefault
   * @return the viewDefault : See field description
   */
  public ViewDefault getViewDefault() {
    return viewDefault;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initDockingViewMap() {
    viewsMap = new ViewMap();
  }
}
