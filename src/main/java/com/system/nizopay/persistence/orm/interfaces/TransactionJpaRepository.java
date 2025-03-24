package com.system.nizopay.persistence.orm.interfaces;

import com.system.nizopay.persistence.orm.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionJpaRepository extends DefaultJpaRepository<TransactionEntity, String>{
}
