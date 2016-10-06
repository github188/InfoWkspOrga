package com.sgu.infowksporga.business.pivot.perspective;

import com.sgu.core.framework.pivot.AbstractIn;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : FindWorkspaceIn class<br>
 */
@Getter
@Setter
public class FindWorkspaceIn extends AbstractIn {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1471001871187L;

  /**
   * Attribute
   */
  private String workspaceId;

  /**
   * Constructor<br>
   */
  public FindWorkspaceIn() {
    super();
  }

}
