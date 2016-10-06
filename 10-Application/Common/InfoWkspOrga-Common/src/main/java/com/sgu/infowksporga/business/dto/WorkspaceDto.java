package com.sgu.infowksporga.business.dto;

import java.io.Serializable;
import java.util.List;

import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.Workspace;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class WorkspaceDto.
 */

@Getter
@Setter
public class WorkspaceDto extends AbstractDto implements Serializable {

  /** serialVersionUID. */
  private static final long serialVersionUID = 5137403047145481279L;

  /** The workspace. */
  private Workspace workspace;

  /** The workspace master. */
  private Workspace workspaceMaster;

  /**
   * The Constructor.
   */
  public WorkspaceDto() {
    super();
    workspace = new Workspace();
  }

  /**
   * Gets the preferred workspaces views dto.
   *
   * @return the preferred workspaces views dto
   */
  public WorkspacesViewsDto buildWorkspacesViewsDtoExtract() {
    return buildWorkspacesViewsDto();
  }

  /**
   * Builds the preferred workspaces views dto.
   *
   * @return the preferred workspaces views dto
   */
  private WorkspacesViewsDto buildWorkspacesViewsDto() {
    WorkspacesViewsDto viewsDto = new WorkspacesViewsDto();

    List<View> views = workspace.getViews();
    if (views != null) {
      for (View view : views) {
        viewsDto.addView(view);
      }
    }

    if (workspaceMaster != null) {
      List<View> masterViews = workspaceMaster.getViews();
      if (masterViews != null) {
        for (View view : masterViews) {
          viewsDto.addMasterView(view);
        }
      }
    }

    return viewsDto;
  }

}
