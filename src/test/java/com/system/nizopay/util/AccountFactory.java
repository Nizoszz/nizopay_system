package com.system.nizopay.util;

import com.system.nizopay.core.model.Account;

import java.math.BigDecimal;

public class AccountFactory{
    public static Account createValidAccount(){
        return Account.create("123456","001","user-123");
    }
    public static Account createValidAccountWithValidCard(){
        var wallet = Account.create("123456","001","user-123");
        wallet.requestCredit();
        wallet.approveCredit(new BigDecimal("1600"));
        wallet.addCreditCard(
                CreditCardFactory.createValidCreditCardNumberWithoutBalanceAndUserId(wallet.getCreditLimit(),wallet.getUserId(), new BigDecimal("1600")));
        return wallet;
    }
    public static Account createAccountWithBalancePositive(String accountNumber,String userId){
        var wallet = Account.create(accountNumber,"001",userId);
        wallet.updateBalance(new BigDecimal("3000"));
        return wallet;
    }
}
