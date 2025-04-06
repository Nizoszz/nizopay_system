package com.system.nizopay.core.service;

import com.system.nizopay.core.exception.ConflictException;
import com.system.nizopay.core.exception.NotFoundException;
import com.system.nizopay.core.model.Account;
import com.system.nizopay.core.model.Transaction;
import com.system.nizopay.core.model.TransactionType;
import com.system.nizopay.core.repository.AccountRepository;
import com.system.nizopay.core.repository.TransactionRepository;
import com.system.nizopay.http.rest.dto.DepositDto;
import com.system.nizopay.http.rest.dto.PaymentDto;
import com.system.nizopay.http.rest.dto.TransferDto;
import com.system.nizopay.http.rest.dto.WithdrawDto;
import com.system.nizopay.persistence.orm.mapper.AccountMapper;
import com.system.nizopay.persistence.orm.mapper.TransactionMapper;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TransactionService{
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Transaction makeTransfer(String payerId, TransferDto data) {
        Account payerAccount = getAccount(payerId, "Payer account not found");
        Account payeeAccount = getAccount(data.payeeId(), "Payee account not found");
        if (payerAccount.getAccountId().equals(payeeAccount.getAccountId())) {
            throw new ConflictException("Cannot transfer to the same account");
        }
        try {
            payerAccount.withdraw(data.amount());
            payeeAccount.deposit(data.amount());
            var sender = Transaction.create(payerAccount.getAccountId(), payeeAccount.getAccountId(),data.amount(), TransactionType.TRANSFER, data.description());
            sender.successfulTransaction();
            this.accountRepository.save(AccountMapper.toEntity(payerAccount));
            this.accountRepository.save(AccountMapper.toEntity(payeeAccount));
            this.transactionRepository.save(TransactionMapper.toEntity(sender));
            return sender;
        } catch (RuntimeException e) {
            this.logFailedTransactionAsync(payerId, payeeAccount.getAccountId(), data.amount(), TransactionType.TRANSFER, data.description());
            throw e;
        }
    }

    @Transactional
    public Transaction makeDeposit(String payeeId, DepositDto data) {
        Account payeeAccount = getAccount(payeeId, "Payee account not found");
        try {
            payeeAccount.deposit(data.amount());
            var deposit = Transaction.create(null,payeeAccount.getAccountId(),data.amount(),
                                             TransactionType.DEPOSIT,data.description());
            deposit.successfulTransaction();
            this.accountRepository.save(AccountMapper.toEntity(payeeAccount));
            return TransactionMapper.toModel(this.transactionRepository.save(TransactionMapper.toEntity(deposit)));
        } catch (RuntimeException e) {
            this.logFailedTransactionAsync(null, payeeId, data.amount(), TransactionType.DEPOSIT, data.description());
            throw e;
        }
    }

    @Transactional
    public Transaction makeWithdraw(String payerId, WithdrawDto data) {
        Account payerAccount = getAccount(payerId, "Payer account not found");
        try {
            payerAccount.withdraw(data.amount());
            var withdraw = Transaction.create(payerAccount.getAccountId(),null,data.amount(),
                                             TransactionType.WITHDRAW,data.description());
            withdraw.successfulTransaction();
            this.accountRepository.save(AccountMapper.toEntity(payerAccount));
            return TransactionMapper.toModel(this.transactionRepository.save(TransactionMapper.toEntity(withdraw)));
        } catch (RuntimeException e) {
            this.logFailedTransactionAsync(payerId, null, data.amount(), TransactionType.WITHDRAW, data.description());
            throw e;
        }
    }
    @Transactional
    public Transaction makePayment(String payerId, PaymentDto data) {
        Account payerAccount = getAccount(payerId, "Payer account not found");
        try {
            payerAccount.withdraw(data.amount());
            var withdraw = Transaction.create(payerAccount.getAccountId(),null,data.amount(),
                                              TransactionType.PAYMENT,data.description());
            withdraw.successfulTransaction();
            this.accountRepository.save(AccountMapper.toEntity(payerAccount));
            return TransactionMapper.toModel(this.transactionRepository.save(TransactionMapper.toEntity(withdraw)));
        } catch (RuntimeException e) {
            this.logFailedTransactionAsync(payerId, null, data.amount(), TransactionType.PAYMENT, data.description());
            throw e;
        }
    }
    private Account getAccount(String accountId, String errorMessage) {
        return accountRepository.findById(accountId)
                .map(AccountMapper::toModel)
                .orElseThrow(() -> new NotFoundException(errorMessage));
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logFailedTransactionAsync(String payerId, String payeeId, BigDecimal amount, TransactionType type, String description) {
        var failedSender = Transaction.create(payerId,payeeId,amount,type,description);
        failedSender.failedTransaction();
        transactionRepository.save(TransactionMapper.toEntity(failedSender));
    }
}
