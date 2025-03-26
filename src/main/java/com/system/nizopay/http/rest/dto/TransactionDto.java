package com.system.nizopay.http.rest.dto;

public record TransactionDto(
        String payerId,
        String payeeId,
        double amount,
        String description){
}
