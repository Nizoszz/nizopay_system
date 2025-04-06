package com.system.nizopay.core.model;

import java.util.Random;

public class CardNumberGenerator{
    private static int isValidCheckSum(String cardNumber) {
        int sum = 0;
        for(char digit : cardNumber.toCharArray()) {
            sum += digit - '0';
        }
        return sum % 10;
    }

    public String generate() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for(int i = 0; i < 8; i++) {
            cardNumber.append(random.nextInt(10));
        }
        cardNumber.append(isValidCheckSum(cardNumber.toString()));
        return cardNumber.toString();
    }
}
