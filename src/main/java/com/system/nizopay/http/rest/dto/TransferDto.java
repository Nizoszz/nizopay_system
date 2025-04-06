package com.system.nizopay.http.rest.dto;

import java.math.BigDecimal;

public record TransferDto(
        String payeeId,
        BigDecimal amount,
        String description
){
}
