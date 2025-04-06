package com.system.nizopay.persistence.orm.repository;

import com.system.nizopay.persistence.orm.entity.AccountEntity;
import com.system.nizopay.persistence.orm.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends DefaultJpaRepository<UserEntity, String>{
    boolean existsByEmail(String email);
}
