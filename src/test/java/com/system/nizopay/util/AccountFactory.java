package com.system.nizopay.util;

import com.system.nizopay.core.model.Account;

import java.math.BigDecimal;

public class AccountFactory{
    public static Account createValidAccount(){
        return Account.create("user-123");
    }
    public static Account createValidAccountWithValidCard(){
        var account = Account.create("user-123");
        account.requestCredit();
        account.approveCredit(new BigDecimal("1600"));
        account.addCreditCard(
                CreditCardFactory.createValidCreditCardNumberWithoutBalanceAndUserId(account.getUserId(), account.getCreditLimit(), new BigDecimal("1600")));
        return account;
    }
    public static Account createAccountWithBalancePositive(String userId){
        var account = Account.create(userId);
        account.deposit(new BigDecimal("3000"));
        return account;
    }
}
