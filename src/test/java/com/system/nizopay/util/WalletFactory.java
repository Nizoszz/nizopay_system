package com.system.nizopay.util;

import com.system.nizopay.core.model.Wallet;

import java.math.BigDecimal;

public class WalletFactory{
    public static Wallet createValidWallet(){
        return Wallet.create("123456", "001", "user-123");
    }
    public static Wallet createValidWalletWithValidCard(){
        var wallet = Wallet.create("123456", "001", "user-123");
        wallet.requestCredit();
        wallet.approveCredit(new BigDecimal("1600"));
        wallet.addCreditCard(
                CreditCardFactory.createValidCreditCardNumberWithoutBalanceAndUserId(wallet.getBalance(),wallet.getUserId()));
        return wallet;
    }
    public static Wallet createWalletWithBalancePositive(String accountNumber, String userId){
        var wallet = Wallet.create(accountNumber, "001", userId);
        wallet.updateBalance(new BigDecimal("3000"));
        return wallet;
    }
}
