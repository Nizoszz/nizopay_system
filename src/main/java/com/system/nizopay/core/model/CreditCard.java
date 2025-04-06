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

    public CreditCard(String cardId, String accountId, String userId,String cardNumber,String cardHolderName,String cvv,YearMonth expirationDate,BigDecimal creditLimit,BigDecimal currentBalance, boolean isCardActive, CardType cardType){
        super(cardId,userId, accountId,cardNumber,cardHolderName,cvv,expirationDate, isCardActive,cardType);
        this.creditLimit = creditLimit;
        this.currentBalance = currentBalance;
    }

    public static CreditCard create(String userId, String accountId,String cardHolderName,
                                    BigDecimal creditLimit,BigDecimal currentBalance){
        String cardId = UUID.randomUUID().toString();
        String cardNumber = new CardNumberGenerator().generate();
        String cvv = String.format("%03d", (int) (Math.random() * 999));
        LocalDate currentDate = LocalDate.now();
        YearMonth expirationDate = YearMonth.from(currentDate).plusYears(3);
        return new CreditCard(cardId,userId,accountId,cardNumber,cardHolderName,cvv,expirationDate,creditLimit,
                              currentBalance, false,CardType.CREDIT);
    }
}
