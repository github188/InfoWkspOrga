package com.sgu.infowksporga.business.dao.api;

import com.sgu.infowksporga.business.entity.Workspace;

public interface IWorkspaceDao {

  /**
   * Find workspace with views and attr.
   *
   * @param workspaceId the workspace id
   * @return the < workspace>
   */
  Workspace findWorkspaceWithViewsAndAttr(String workspaceId);

}
