package com.sgu.infowksporga.business.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sgu.core.framework.dao.GJpaRepository;
import com.sgu.infowksporga.business.entity.ViewAttribute;

/**
 * {@link Resource} repository
 */
public interface ViewAttributeRepository extends GJpaRepository<ViewAttribute, Integer> {

  /**
   * Removes the views.
   *
   * @param viewsIdToDelete the views id to delete
   * @return the number of deleted items
   */
  @Query("DELETE FROM ViewAttribute va WHERE va.viewId in :viewsIdToDelete")
  public int removeViewsAttributes(@Param("viewsIdToDelete") List<Integer> viewsIdToDelete);

}