package com.system.nizopay.http.rest.dto;

import java.math.BigDecimal;

public record DepositDto(
        BigDecimal amount,
        String description
){
}
