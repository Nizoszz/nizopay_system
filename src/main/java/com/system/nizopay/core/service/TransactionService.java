package com.system.nizopay.core.service;

import com.system.nizopay.core.exception.ConflictException;
import com.system.nizopay.core.exception.NotFoundException;
import com.system.nizopay.core.model.Account;
import com.system.nizopay.core.model.Transaction;
import com.system.nizopay.core.model.TransactionType;
import com.system.nizopay.core.repository.AccountRepository;
import com.system.nizopay.core.repository.TransactionRepository;
import com.system.nizopay.http.rest.dto.TransactionDto;
import com.system.nizopay.persistence.orm.mapper.AccountMapper;
import com.system.nizopay.persistence.orm.mapper.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TransactionService{
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Transaction makeTransfer(TransactionDto data) {
        Account payerAccount = accountRepository.findById(data.payerId()).map(AccountMapper::toModel).orElseThrow(() -> new NotFoundException("Payer account not found"));
        Account payeeAccount = accountRepository.findById(data.payeeId()).map(AccountMapper::toModel).orElseThrow(() -> new NotFoundException("Payee account not found"));
        if (payerAccount.getAccountId().equals(payeeAccount.getAccountId())) {
            throw new ConflictException("Cannot transfer to the same account");
        }
        try {
            BigDecimal amount = BigDecimal.valueOf(data.amount());
            payerAccount.withdraw(amount);
            payeeAccount.deposit(amount);
            var sender = Transaction.create(payerAccount.getAccountId(), payeeAccount.getAccountId(),data.amount(), TransactionType.TRANSFER, data.description());
            sender.successfulTransaction();
            this.transactionRepository.save(TransactionMapper.toEntity(sender));
            return sender;
        } catch (Exception e) {
            var failedSender = Transaction.create(payerAccount.getAccountId(), payeeAccount.getAccountId(),data.amount(), TransactionType.TRANSFER, data.description());
            failedSender.failedTransaction();
            this.transactionRepository.save(TransactionMapper.toEntity(failedSender));
            throw e;
        }
    }

    @Transactional
    public Transaction makeDeposit(TransactionDto data) {
        Account payeeAccount = AccountMapper.toModel(accountRepository.findById(data.payeeId()).orElseThrow(() -> new NotFoundException("Payee account not found")));
        try {
            BigDecimal amount = BigDecimal.valueOf(data.amount());
            payeeAccount.deposit(amount);
            var deposit = Transaction.create(null,payeeAccount.getAccountId(),data.amount(),
                                             TransactionType.DEPOSIT,data.description());
            deposit.successfulTransaction();
            return TransactionMapper.toModel(this.transactionRepository.save(TransactionMapper.toEntity(deposit)));
        } catch (Exception e) {
            var failedSender = Transaction.create(null, payeeAccount.getAccountId(),data.amount(), TransactionType.DEPOSIT, data.description());
            failedSender.failedTransaction();
            this.transactionRepository.save(TransactionMapper.toEntity(failedSender));
            throw e;
        }
    }

    @Transactional
    public Transaction makeWithdraw(TransactionDto data) {
        Account payerAccount = AccountMapper.toModel(accountRepository.findById(data.payerId()).orElseThrow(() -> new NotFoundException("Payer account not found")));
        try {
            BigDecimal amount = BigDecimal.valueOf(data.amount());
            payerAccount.withdraw(amount);
            var withdraw = Transaction.create(payerAccount.getAccountId(),null,data.amount(),
                                             TransactionType.WITHDRAW,data.description());
            withdraw.successfulTransaction();
            return TransactionMapper.toModel(this.transactionRepository.save(TransactionMapper.toEntity(withdraw)));
        } catch (Exception e) {
            var failedSender = Transaction.create(payerAccount.getAccountId(), null,data.amount(), TransactionType.WITHDRAW, data.description());
            failedSender.failedTransaction();
            this.transactionRepository.save(TransactionMapper.toEntity(failedSender));
            throw e;
        }
    }
    public Transaction makePayment(TransactionDto data) {
        Account payerAccount = AccountMapper.toModel(accountRepository.findById(data.payerId()).orElseThrow(() -> new NotFoundException("Payer account not found")));
        try {
            BigDecimal amount = BigDecimal.valueOf(data.amount());
            payerAccount.withdraw(amount);
            var withdraw = Transaction.create(payerAccount.getAccountId(),null,data.amount(),
                                              TransactionType.PAYMENT,data.description());
            withdraw.successfulTransaction();
            return TransactionMapper.toModel(this.transactionRepository.save(TransactionMapper.toEntity(withdraw)));
        } catch (Exception e) {
            var failedSender = Transaction.create(payerAccount.getAccountId(), null,data.amount(), TransactionType.PAYMENT, data.description());
            failedSender.failedTransaction();
            this.transactionRepository.save(TransactionMapper.toEntity(failedSender));
            throw e;
        }
    }
}
