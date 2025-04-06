package com.system.nizopay.core.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

@Getter
public class CreditCard extends Card{
    private BigDecimal creditLimit;
    private BigDecimal currentBalance;

    public CreditCard(String cardId, String userId, String accountId,String cardNumber,String cardHolderName,String cvv,LocalDate expirationDate,BigDecimal creditLimit,BigDecimal currentBalance, boolean isCardActive, CardType cardType){
        super(cardId,userId, accountId,cardNumber,cardHolderName,cvv,expirationDate, isCardActive,cardType);
        this.creditLimit = creditLimit;
        this.currentBalance = currentBalance;
    }

    public static CreditCard create(String userId, String accountId,String cardHolderName,
                                    BigDecimal creditLimit,BigDecimal currentBalance){
        String cardId = UUID.randomUUID().toString();
        String cardNumber = new CardNumberGenerator().generate();
        String cvv = String.format("%03d", (int) (Math.random() * 999));
        LocalDate expirationDate = LocalDate.now().plusYears(3).withDayOfMonth(1);
        return new CreditCard(cardId,userId,accountId,cardNumber,cardHolderName,cvv,expirationDate,creditLimit,
                              currentBalance, false,CardType.CREDIT);
    }
}
