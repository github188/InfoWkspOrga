package com.sgu.infowksporga.business.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sgu.core.framework.dao.GJpaRepository;
import com.sgu.infowksporga.business.entity.Workspace;

/**
 * {@link Resource} repository
 */
public interface WorkspaceRepository extends GJpaRepository<Workspace, String> {

  /**
   * Selection all attached workspaces to a perspective.
   *
   * @param perspectiveId the perspective id
   * @return the list< workspace>
   */
  @Query("SELECT w FROM Workspace w, PerspectiveWorkspaces pw WHERE pw.perspectiveId = :perspectiveId AND w.id = pw.workspaceId")
  public List<Workspace> findWorkspaces(@Param("perspectiveId") Integer perspectiveId);

}