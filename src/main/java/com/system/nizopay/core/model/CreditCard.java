package com.system.nizopay.core.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.YearMonth;

@Getter
public class CreditCard extends Card{
    private BigDecimal creditLimit;
    private BigDecimal balance;

    public CreditCard(String userId, String cardNumber, String cardHolderName, YearMonth expirationDate,
                      BigDecimal creditLimit, BigDecimal balance) {
        super(userId, cardNumber, cardHolderName, expirationDate);
        this.creditLimit = creditLimit;
        this.balance = balance;
    }

}
