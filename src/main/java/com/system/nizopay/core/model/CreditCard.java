package com.system.nizopay.core.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

@Getter
public class CreditCard extends Card{
    private BigDecimal creditLimit;
    private BigDecimal currentBalance;

    public CreditCard(String cardId, String accountId, String userId,String cardNumber,String cardHolderName,String cvv,YearMonth expirationDate,BigDecimal creditLimit,BigDecimal currentBalance, boolean isCardActive, CardType cardType){
        super(cardId,userId, accountId,cardNumber,cardHolderName,cvv,expirationDate, isCardActive,cardType);
        this.creditLimit = creditLimit;
        this.currentBalance = currentBalance;
    }

    public static CreditCard create(String userId, String accountId,String cardNumber,String cardHolderName, String cvv,YearMonth expirationDate,
                                    BigDecimal creditLimit,BigDecimal currentBalance){
        String cardId = UUID.randomUUID().toString();
        return new CreditCard(cardId,userId,accountId,cardNumber,cardHolderName,cvv,expirationDate,creditLimit,
                              currentBalance, false,CardType.CREDIT);
    }
}
