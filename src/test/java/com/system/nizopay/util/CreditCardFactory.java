package com.system.nizopay.util;

import com.system.nizopay.core.model.CreditCard;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public class CreditCardFactory {
    public static CreditCard createValidCreditCardNumberWithoutBalanceAndUserId(String userId,BigDecimal creditLimit,BigDecimal currentBalance) {
        return CreditCard.create(userId, "test-account","4532111122223333", "User Test","000",
                                 YearMonth.from(LocalDate.of(2026, 3, 11)),
                                 creditLimit, currentBalance);
    }
}

