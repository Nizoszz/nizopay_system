package com.system.nizopay.persistence.orm.mapper;

import com.system.nizopay.core.model.Transaction;
import com.system.nizopay.persistence.orm.entity.TransactionEntity;

public class TransactionMapper{

    public static TransactionEntity toEntity(Transaction transaction){
        return new TransactionEntity(transaction.getTransactionId(),transaction.getPayerId(),transaction.getPayeeId(),
                                     transaction.getAmount(), transaction.getTransactionStatus(), transaction.getTransactionType(), transaction.getDescription(), transaction.getCreatedAt());
    }
    public static Transaction toModel(TransactionEntity transactionEntity){
        return new Transaction(transactionEntity.getTransactionId(), transactionEntity.getPayerId(),
                               transactionEntity.getPayeeId(),transactionEntity.getAmount(), transactionEntity.getTransactionStatus(), transactionEntity.getTransactionType(),
                               transactionEntity.getDescription(), transactionEntity.getCreatedAt());
    }
}
