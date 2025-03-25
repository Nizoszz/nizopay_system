package com.system.nizopay.core.service;

import com.system.nizopay.core.model.Transaction;
import com.system.nizopay.core.repository.AccountRepository;
import com.system.nizopay.core.repository.TransactionRepository;
import com.system.nizopay.persistence.orm.entity.TransactionEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService{
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Transaction save(Transaction data) {
        var payerAccount = accountRepository.findById(data.getPayerId()).orElseThrow(() -> new IllegalArgumentException("Invalid from account id"));
        var payeeAccount = accountRepository.findById(data.getPayeeId()).orElseThrow(() -> new IllegalArgumentException("Invalid from account id"));
        payerAccount.withdraw(data.getAmount());

        return TransactionEntity.toModel(this.transactionRepository.save(TransactionEntity.toEntity(data)));
    }
}
