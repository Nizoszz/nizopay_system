package com.system.nizopay.core.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Transaction{
    private String transactionId;
    private String payerId;
    private String payeeId;
    private BigDecimal amount;
    private TransactionStatus transactionStatus;
    private TransactionType transactionType;
    private String description;
    private LocalDateTime createdAt;


    public Transaction(String transactionId,String payerId,String payeeId,BigDecimal amount,TransactionStatus transactionStatus,TransactionType transactionType,String description,LocalDateTime createdAt){
        this.transactionId = transactionId;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
        this.description = description;
        this.createdAt = createdAt;
    }

    public static Transaction create(String payerId,String payeeId,BigDecimal amount,TransactionType transactionType,String description){
        LocalDateTime createdAt = LocalDateTime.now();
        String id = UUID.randomUUID().toString();
        return new Transaction(id, payerId, payeeId,amount,TransactionStatus.PENDING,transactionType,description != null ?description: "",createdAt);
    }

    public void failedTransaction(){
        this.transactionStatus = TransactionStatus.FAILED;
    }
    public void successfulTransaction(){
        this.transactionStatus = TransactionStatus.SUCCESSFUL;
    }
    public void refundTransaction(){
        this.transactionStatus = TransactionStatus.REFUNDED;
    }
}
