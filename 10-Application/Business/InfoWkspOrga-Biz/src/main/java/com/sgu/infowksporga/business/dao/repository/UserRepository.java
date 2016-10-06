package com.sgu.infowksporga.business.dao.repository;

import com.sgu.core.framework.dao.GJpaRepository;
import com.sgu.infowksporga.business.entity.User;

/**
 * {@link Resource} repository
 */
public interface UserRepository extends GJpaRepository<User, Integer> {
  // Everything is delegated
}