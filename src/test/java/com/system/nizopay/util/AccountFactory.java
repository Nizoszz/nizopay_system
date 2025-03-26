package com.system.nizopay.util;

import com.system.nizopay.core.model.Account;

import java.math.BigDecimal;

public class AccountFactory{
    public static Account createValidAccount(){
        return Account.create("123456","001","user-123");
    }
    public static Account createValidAccountWithValidCard(){
        var account = Account.create("123456","001","user-123");
        account.requestCredit();
        account.approveCredit(new BigDecimal("1600"));
        account.addCreditCard(
                CreditCardFactory.createValidCreditCardNumberWithoutBalanceAndUserId(account.getUserId(), account.getCreditLimit(), new BigDecimal("1600")));
        return account;
    }
    public static Account createAccountWithBalancePositive(String accountNumber,String userId){
        var account = Account.create(accountNumber,"001",userId);
        account.deposit(new BigDecimal("3000"));
        return account;
    }
}
