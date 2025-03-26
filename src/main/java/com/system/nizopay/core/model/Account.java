package com.system.nizopay.core.model;

import com.system.nizopay.core.exception.ConflictException;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Getter
public class Account{
    private String accountId;
    private String accountNumber;
    private String agency;
    private String userId;
    private boolean isActive;
    private BigDecimal balance;
    private BigDecimal creditLimit;
    private AccountStatus accountStatus;
    private List<Card> cards;
    private List<Transaction> transactions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Account(String accountId,String accountNumber,String agency,String userId,boolean isActive,LocalDateTime createdAt,LocalDateTime updatedAt){
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.agency = agency;
        this.userId = userId;
        this.isActive = isActive;
        this.balance = BigDecimal.ZERO;
        this.creditLimit = BigDecimal.ZERO;
        this.accountStatus = AccountStatus.NOT_REQUESTED;
        this.cards = new ArrayList<Card>();
        this.transactions = new ArrayList<Transaction>();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = null;
    }

    public Account(String accountId,String accountNumber,String agency,String userId,boolean isActive,BigDecimal balance,BigDecimal creditLimit,AccountStatus accountStatus,List<Card> cards,List<Transaction> transactions,LocalDateTime createdAt,LocalDateTime updatedAt,LocalDateTime deletedAt){
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.agency = agency;
        this.userId = userId;
        this.isActive = isActive;
        this.balance = balance;
        this.creditLimit = creditLimit;
        this.accountStatus = accountStatus;
        this.cards = cards;
        this.transactions = transactions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Account create(String accountNumber,String agency,String userId){
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        return new Account(UUID.randomUUID().toString(),accountNumber,agency,userId,true,createdAt,updatedAt);
    }
    public static Account restore(String accountId,String accountNumber,String agency,String userId,boolean active,BigDecimal balance,BigDecimal creditLimit,AccountStatus accountStatus,List<Transaction> transactions,List<Card> cards,LocalDateTime createdAt,LocalDateTime updatedAt,LocalDateTime deletedAt){
        return new Account(accountId,accountNumber,agency,userId,active,balance,creditLimit,accountStatus,cards,
                           transactions,createdAt,updatedAt,deletedAt);
    }

    public void addCreditCard(CreditCard card) {
        if(!this.accountStatus.equals(AccountStatus.APPROVED)){
            throw new ConflictException("Credit must be approved before adding a new card.");
        }
        if (this.cards.contains(card)) {
            throw new ConflictException("Card is already associated with this wallet.");
        }
        if (!this.userId.equals(card.getUserId())) {
            throw new ConflictException("Card belongs to a different user.");
        }

        this.cards.add(card);
        this.updatedAt = LocalDateTime.now();
    }

    public void removeCard(Card card) {
        if (!this.cards.contains(card)) {
            throw new ConflictException("Card is not associated with this account.");
        }

        this.cards.remove(card);
        this.updatedAt = LocalDateTime.now();
    }

    public void requestCredit(){
        if (this.accountStatus != AccountStatus.NOT_REQUESTED) {
            throw new ConflictException("Credit can only be approved when in PENDING status.");
        }
        this.accountStatus = AccountStatus.PENDING;
    }

    public void approveCredit(BigDecimal newLimit){
        if (this.accountStatus != AccountStatus.PENDING) {
            throw new ConflictException("Credit can only be approved when in PENDING status.");
        }
        if(this.creditLimit.compareTo(BigDecimal.ZERO) < 0 ||
                newLimit.compareTo(BigDecimal.ZERO) < 0){
            throw new ConflictException("Credit limit must be a positive value.");
        }
        this.creditLimit = newLimit;
        this.accountStatus = AccountStatus.APPROVED;
        this.updatedAt = LocalDateTime.now();
    }

    public void rejectCredit(){
        if (this.accountStatus != AccountStatus.PENDING) {
            throw new ConflictException("Credit can only be rejected when in PENDING status.");
        }
        this.accountStatus = AccountStatus.REJECTED;
        this.updatedAt = LocalDateTime.now();
    }

    public void withdraw(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new ConflictException("Amount must be a positive value.");
        }
        if(this.balance.compareTo(amount) < 0){
            throw new ConflictException("Insufficient balance.");
        }
        this.balance = this.balance.subtract(amount);
        this.updatedAt = LocalDateTime.now();
    }
    public void deposit(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new ConflictException("Amount must be a positive value.");
        }
        this.balance = this.balance.add(amount);
        this.updatedAt = LocalDateTime.now();
    }
}
