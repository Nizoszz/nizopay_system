package com.system.nizopay.persistence.orm.repository;

import com.system.nizopay.persistence.orm.entity.AccountEntity;
import com.system.nizopay.persistence.orm.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaRepository extends DefaultJpaRepository<AccountEntity, String>{
}
