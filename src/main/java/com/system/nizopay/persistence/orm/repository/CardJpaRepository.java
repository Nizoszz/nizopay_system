package com.system.nizopay.persistence.orm.repository;

import com.system.nizopay.persistence.orm.entity.AccountEntity;
import com.system.nizopay.persistence.orm.entity.CardEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CardJpaRepository extends DefaultJpaRepository<CardEntity, String>{
}
