package com.sgu.infowksporga.business.pivot.workspace;

import com.sgu.core.framework.pivot.AbstractOut;
import com.sgu.infowksporga.business.entity.Workspace;

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
  private static final long serialVersionUID = 1171122829990L;

  /** The workspace. */
  private Workspace workspace;

  /**
   * Constructor<br>
   */
  public SaveWorkspaceOut(final Workspace workspace) {
    super();
    this.workspace = workspace;
  }

}
