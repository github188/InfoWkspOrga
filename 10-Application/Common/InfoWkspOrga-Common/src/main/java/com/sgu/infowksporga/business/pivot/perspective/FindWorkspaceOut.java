package com.sgu.infowksporga.business.pivot.perspective;

import com.sgu.core.framework.pivot.AbstractOut;
import com.sgu.infowksporga.business.dto.WorkspaceDto;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : FindWorkspaceOut class<br>
 */
@Getter
@Setter
public class FindWorkspaceOut extends AbstractOut {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1471001871200L;

  /**
   * Attribute
   */
  private WorkspaceDto workspaceViews;

  /**
   * Constructor<br>
   */
  public FindWorkspaceOut() {
    super();
  }

}
