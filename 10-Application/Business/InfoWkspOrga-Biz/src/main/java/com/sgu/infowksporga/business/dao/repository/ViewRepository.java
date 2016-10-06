package com.sgu.infowksporga.business.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sgu.core.framework.dao.GJpaRepository;
import com.sgu.infowksporga.business.entity.View;

/**
 * {@link Resource} repository
 */
public interface ViewRepository extends GJpaRepository<View, Integer> {

  /**
   * Find views not in given list for workspace.
   *
   * @param viewsIdToKeep the views id to keep
   * @param workspaceId the workspace id
   * @return the list of views Id to Delete
   */
  @Query("SELECT v.id FROM View v WHERE v.workspaceId = :wrkspId and v.id not in :viewsIdToKeep")
  public List<Integer> findViewsNotInGivenListForWorkspace(@Param("viewsIdToKeep") List<Integer> viewsIdToKeep,
  @Param("wrkspId") String workspaceId);

  /**
   * Find workspace views.
   *
   * @param wrkspId the wrksp id
   * @return the list< integer>
   */
  @Query("SELECT v.id FROM View v WHERE v.workspaceId = :wrkspId")
  public List<Integer> findWorkspaceViews(@Param("wrkspId") String wrkspId);

  /**
   * Removes the views.
   *
   * @param viewsIdToDelete the views id to delete
   * @return the number of deleted items
   */
  @Query("DELETE FROM View v WHERE v.id in :viewsIdToDelete")
  public int removeViews(@Param("viewsIdToDelete") List<Integer> viewsIdToDelete);

}