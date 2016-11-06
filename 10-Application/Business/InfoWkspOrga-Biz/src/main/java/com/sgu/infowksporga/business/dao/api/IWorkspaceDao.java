package com.sgu.infowksporga.business.dao.api;

import java.util.Date;

import com.sgu.infowksporga.business.entity.Workspace;

public interface IWorkspaceDao {

  /**
   * Find workspace with views and attr.
   *
   * @param workspaceId the workspace id
   * @return the < workspace>
   */
  Workspace findWorkspaceWithViewsAndAttr(String workspaceId);

  /**
   * Update workspace layout.
   *
   * @param workspaceId the workspace id
   * @param layout the layout
   * @param width the width
   * @param height the height
   * @param userLogin the user login
   * @param saveDate the save date
   */
  void updateWorkspaceLayout(String workspaceId, String layout, Double width, Double height, String userLogin, Date saveDate);

  /**
   * Update workspace properties.
   * Not Layout nor Views
   *
   * @param workspace the workspace
   * @param userLogin the user login
   * @param saveDate the save date
   */
  void updateWorkspaceProperties(final Workspace workspace, final String userLogin, final Date saveDate);
}
