package com.system.nizopay.persistence.orm.repository;

import com.system.nizopay.persistence.orm.entity.AccountEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CardJpaRepository extends DefaultJpaRepository<AccountEntity, String>{
}
