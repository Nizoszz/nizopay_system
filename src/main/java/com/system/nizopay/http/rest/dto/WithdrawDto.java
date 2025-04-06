package com.system.nizopay.http.rest.dto;

import java.math.BigDecimal;

public record WithdrawDto(
        BigDecimal amount,
        String description
){
}
