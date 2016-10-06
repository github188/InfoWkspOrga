package com.sgu.infowksporga.business.dao.repository;

import com.sgu.core.framework.dao.GJpaRepository;
import com.sgu.infowksporga.business.entity.Preference;

/**
 * {@link Resource} repository
 */
public interface PreferenceRepository extends GJpaRepository<Preference, Integer> {
  // Everything is delegated
}