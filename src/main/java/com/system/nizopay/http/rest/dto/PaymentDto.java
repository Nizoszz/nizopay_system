package com.system.nizopay.http.rest.dto;

import java.math.BigDecimal;

public record PaymentDto(
        BigDecimal amount,
        String description
){
}
