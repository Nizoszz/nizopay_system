package com.system.nizopay.http.rest.dto;

import java.math.BigDecimal;

public record CreditCardDto(String accountId,String cardHolderName,
                            BigDecimal creditLimit,BigDecimal currentBalance){
}
