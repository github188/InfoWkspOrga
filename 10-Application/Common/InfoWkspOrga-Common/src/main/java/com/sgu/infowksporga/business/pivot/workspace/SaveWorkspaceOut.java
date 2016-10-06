package com.sgu.infowksporga.business.pivot.workspace;

import com.sgu.core.framework.pivot.AbstractOut;
import com.sgu.infowksporga.business.dto.WorkspaceDto;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : SaveWorkspaceOut class<br>
 */
@Getter
@Setter
public class SaveWorkspaceOut extends AbstractOut {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1471122829990L;

  /** The workspace dto. */
  private WorkspaceDto workspaceDto;

  /**
   * Constructor<br>
   */
  public SaveWorkspaceOut(WorkspaceDto workspaceDto) {
    super();
    this.workspaceDto = workspaceDto;
  }

}
