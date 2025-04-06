package com.system.nizopay.util;

import com.system.nizopay.core.model.CreditCard;

import java.math.BigDecimal;

public class CreditCardFactory {
    public static CreditCard createValidCreditCardNumberWithoutBalanceAndUserId(String userId,BigDecimal creditLimit,BigDecimal currentBalance) {
        return CreditCard.create(userId, "test-account","User Test",
                                 creditLimit, currentBalance);
    }
}

