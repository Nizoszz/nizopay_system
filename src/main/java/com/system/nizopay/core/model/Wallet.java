package com.system.nizopay.core.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.*;

@Getter
public class Wallet{
    private String accountId;
    private String accountNumber;
    private String agency;
    private String userId;
    private boolean isActive;
    private BigDecimal balance;
    private BigDecimal creditLimit;
    private WalletStatus walletStatus;
    private List<Card> cards;
    private Date createdAt;
    private Date updatedAt;
    private Optional<Date> deletedAt;

    public Wallet(String accountId,String accountNumber,String agency,String userId,boolean isActive,Date createdAt,Date updatedAt){
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.agency = agency;
        this.userId = userId;
        this.isActive = isActive;
        this.balance = BigDecimal.ZERO;
        this.creditLimit = BigDecimal.ZERO;
        this.walletStatus = WalletStatus.NOT_REQUESTED;
        this.cards = new ArrayList<Card>();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = Optional.empty();
    }



    public static Wallet create(String accountNumber,String agency,String userId){
        Date createdAt = new Date();
        Date updatedAt = new Date();
        return new Wallet(UUID.randomUUID().toString(),accountNumber,agency,userId, true, createdAt,updatedAt);
    }

    public void addCreditCard(CreditCard card) {
        if(!this.walletStatus.equals(WalletStatus.APPROVED)){
            throw new IllegalArgumentException("Credit must be approved before adding a new card.");
        }
        if (this.cards.contains(card)) {
            throw new IllegalArgumentException("Card is already associated with this wallet.");
        }
        if (!this.userId.equals(card.getUserId())) {
            throw new IllegalArgumentException("Card belongs to a different user.");
        }

        this.cards.add(card);
        this.updatedAt = new Date();
    }

    public void removeCard(Card card) {
        if (!this.cards.contains(card)) {
            throw new IllegalArgumentException("Card is not associated with this wallet.");
        }

        this.cards.remove(card);
        this.updatedAt = new Date();
    }

    public void requestCredit(){
        if (this.walletStatus != WalletStatus.NOT_REQUESTED) {
            throw new IllegalArgumentException("Credit can only be approved when in PENDING status.");
        }
        this.walletStatus = WalletStatus.PENDING;
    }

    public void approveCredit(BigDecimal newLimit){
        if (this.walletStatus != WalletStatus.PENDING) {
            throw new IllegalArgumentException("Credit can only be approved when in PENDING status.");
        }
        if(this.creditLimit.compareTo(BigDecimal.ZERO) < 0 ||
                newLimit.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Credit limit must be a positive value.");
        }
        this.creditLimit = newLimit;
        this.walletStatus = WalletStatus.APPROVED;
        this.updatedAt = new Date();
    }

    public void rejectCredit(){
        if (this.walletStatus != WalletStatus.PENDING) {
            throw new IllegalArgumentException("Credit can only be rejected when in PENDING status.");
        }
        this.walletStatus = WalletStatus.REJECTED;
        this.updatedAt = new Date();
    }

    public void updateBalance(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Amount must be a positive value.");
        }
        this.balance = this.balance.add(amount);
        this.updatedAt = new Date();
    }
}
