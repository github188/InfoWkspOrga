package com.sgu.infowksporga.business.pivot.workspace;

import java.util.LinkedHashMap;
import java.util.Map;

import com.sgu.core.framework.pivot.AbstractIn;
import com.sgu.infowksporga.business.entity.Workspace;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : SaveWorkspaceIn class<br>.
 */

@Getter
@Setter
public class SaveWorkspaceIn extends AbstractIn {

  /** The attribute serialVersionUID. */
  private static final long serialVersionUID = 1471122829982L;

  /** The workspace properties to save. */
  private Workspace workspace;

  /** The perspective id. */
  private Integer perspectiveId;

  /**
   * The new workspaces order in the current perspective
   * cf workspace.perspectiveId transient property.
   * Map<WorkspaceId, newOrder>
   */
  private Map<String, Integer> newWorkspacesOrder;

  /**
   * Constructor<br>.
   */
  public SaveWorkspaceIn() {
    super();
    this.newWorkspacesOrder = new LinkedHashMap<String, Integer>(10);
  }

}
