package com.system.nizopay.util;

import com.system.nizopay.domain.Wallet;

import java.math.BigDecimal;

public class WalletCreator{
    public static Wallet createValidWallet(){
        return Wallet.create("123456", "001", "user-123");
    }
    public static Wallet createValidWalletWithValidCard(){
        var wallet = Wallet.create("123456", "001", "user-123");
        wallet.requestCredit();
        wallet.approveCredit(new BigDecimal("1600"));
        wallet.addCreditCard(CreditCardCreator.createValidCreditCardNumberWithoutBalanceAndUserId(wallet.getBalance(), wallet.getUserId()));
        return wallet;
    }
}
