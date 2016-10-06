package com.sgu.infowksporga.business.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sgu.core.framework.dao.GJpaRepository;
import com.sgu.infowksporga.business.entity.PerspectiveWorkspaces;

/**
 * {@link Resource} repository
 */
public interface PerspectiveWorkspacesRepository extends GJpaRepository<PerspectiveWorkspaces, Integer> {

  /**
   * Find all perspective children workspaces.
   *
   * @param perspectiveId the perspective id
   * @return the list< perspective workspaces>
   */
  @Query("SELECT pw, w FROM PerspectiveWorkspaces pw, Workspace w WHERE pw.perspectiveId = :perspectiveId AND w.id = pw.workspaceId order by pw.workspaceOrder")
  List<PerspectiveWorkspaces> findAllChildrenWorkspaces(@Param("perspectiveId") Integer perspectiveId);

  /**
   * Find perspective workspace link.
   * Used to check if workspace is already attached to a perspective
   *
   * @param perspectiveId the perspective id
   * @param workspaceId the workspace id
   * @return the list< perspective workspaces>
   */
  @Query("SELECT pw FROM PerspectiveWorkspaces pw WHERE pw.perspectiveId = :perspectiveId AND pw.workspaceId = :workspaceId")
  List<PerspectiveWorkspaces> findPerspectiveWorkspaceLink(@Param("perspectiveId") Integer perspectiveId,
  @Param("workspaceId") String workspaceId);
}