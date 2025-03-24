package com.system.nizopay.core.service;

import com.system.nizopay.core.model.Transaction;
import com.system.nizopay.core.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService{
    private final TransactionRepository transactionRepository;
    public void save(Transaction data) {
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
