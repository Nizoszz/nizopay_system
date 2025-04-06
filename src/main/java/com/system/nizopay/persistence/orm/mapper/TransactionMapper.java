package com.system.nizopay.persistence.orm.mapper;

import com.system.nizopay.core.model.Transaction;
import com.system.nizopay.persistence.orm.entity.TransactionEntity;

public class TransactionMapper {

    public static TransactionEntity toEntity(
            Transaction transaction
    ) {

        return new TransactionEntity(
                transaction.getTransactionId(),
                transaction.getPayerId(),
                transaction.getPayeeId(),
                transaction.getAmount(),
                transaction.getTransactionStatus(),
                transaction.getTransactionType(),
                transaction.getDescription(),
                transaction.getCreatedAt()
        );
    }

    public static Transaction toModel(TransactionEntity entity) {
        return new Transaction(
                entity.getTransactionId(),
                entity.getPayerId(),
                entity.getPayeeId(),
                entity.getAmount(),
                entity.getTransactionStatus(),
                entity.getTransactionType(),
                entity.getDescription(),
                entity.getCreatedAt()
        );
    }
}
