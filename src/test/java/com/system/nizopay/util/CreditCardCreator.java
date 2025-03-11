package com.system.nizopay.util;

import com.system.nizopay.domain.CreditCard;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public class CreditCardCreator{
    public static CreditCard createValidCreditCardNumberWithoutBalanceAndUserId(BigDecimal balance, String userId) {
        return new CreditCard(userId,"4532 1111 2222 3333","User Test",
                                             YearMonth.from(LocalDate.of(2026,
                                                                         3,11)),new BigDecimal("200"), balance);
    }
}
