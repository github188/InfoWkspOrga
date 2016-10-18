package com.sgu.infowksporga.business.dto;

import java.io.Serializable;

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
    this(new Workspace());
  }

  /**
   * The Constructor.
   *
   * @param workspace the workspace
   */
  public WorkspaceDto(final Workspace workspace) {
    this(workspace, null);

  }

  /**
   * The Constructor.
   *
   * @param workspace the workspace
   * @param workspaceMaster the workspace master
   */
  public WorkspaceDto(final Workspace workspace, final Workspace workspaceMaster) {
    super();
    this.workspace = workspace;
    this.workspaceMaster = workspaceMaster;
  }

}
