package com.system.nizopay.core.model;

import lombok.Getter;

import java.time.YearMonth;

@Getter
public abstract class Card{
    private String cardId;
    private String userId;
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private YearMonth expirationDate;
    private boolean isCardActive;


    public Card(String userId, String cardNumber,String cardHolderName,YearMonth expirationDate){
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.isCardActive = true;
    }

}
