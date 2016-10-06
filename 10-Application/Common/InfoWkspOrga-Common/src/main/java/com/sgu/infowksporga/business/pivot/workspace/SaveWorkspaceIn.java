package com.sgu.infowksporga.business.pivot.workspace;

import com.sgu.core.framework.pivot.AbstractIn;
import com.sgu.infowksporga.business.dto.PerspectiveWorkspaceOrderDto;
import com.sgu.infowksporga.business.dto.WorkspaceDto;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : SaveWorkspaceIn class<br>
 */
@Getter
@Setter
public class SaveWorkspaceIn extends AbstractIn {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1471122829982L;

  /**
   * Attribute
   */
  private WorkspaceDto workspaceDto;

  /** The perspective workspace order dto. */
  private PerspectiveWorkspaceOrderDto perspectiveWorkspaceOrderDto;

  /**
   * Constructor<br>
   */
  public SaveWorkspaceIn() {
    super();
  }

  /**
   * The Constructor.
   *
   * @param workspaceDto the workspace dto
   */
  public SaveWorkspaceIn(WorkspaceDto workspaceDto) {
    this.workspaceDto = workspaceDto;
  }

}
