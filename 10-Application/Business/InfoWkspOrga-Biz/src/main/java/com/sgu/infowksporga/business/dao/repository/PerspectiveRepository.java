package com.sgu.infowksporga.business.dao.repository;

import com.sgu.core.framework.dao.GJpaRepository;
import com.sgu.infowksporga.business.entity.Perspective;

/**
 * {@link Resource} repository
 */
public interface PerspectiveRepository extends GJpaRepository<Perspective, Integer> {
  // Everything is delegated
}