package com.system.nizopay.core.model;

import lombok.Getter;

import java.time.YearMonth;

@Getter
public class Card{
    private String cardId;
    private String userId;
    private String accountId;
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private YearMonth expirationDate;
    private boolean isCardActive;
    private CardType cardType;

    public Card(String cardId,String userId,String accountId,String cardNumber,String cardHolderName,String cvv,YearMonth expirationDate,boolean isCardActive,CardType cardType){
        this.cardId = cardId;
        this.userId = userId;
        this.accountId = accountId;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.isCardActive = isCardActive;
        this.cardType = cardType;
    }

    public boolean isValid() {
        return isCardActive &&
                expirationDate.isAfter(YearMonth.now());
    }
    public void block() {
        this.isCardActive = false;
    }

    public void activate() {
        this.isCardActive = true;
    }
}
