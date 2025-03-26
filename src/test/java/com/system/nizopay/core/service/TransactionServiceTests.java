package com.system.nizopay.core.service;

import com.system.nizopay.core.model.*;
import com.system.nizopay.core.repository.AccountRepository;
import com.system.nizopay.core.repository.TransactionRepository;
import com.system.nizopay.http.rest.dto.TransactionDto;
import com.system.nizopay.persistence.orm.entity.TransactionEntity;
import com.system.nizopay.persistence.orm.mapper.AccountMapper;
import com.system.nizopay.persistence.orm.mapper.TransactionMapper;
import com.system.nizopay.util.AccountFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTests{
    @InjectMocks
    private TransactionService transactionService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;
    private Account accountUser1;
    private Account accountUser2;

    @BeforeEach
    void setUp() {
        accountUser1 = AccountFactory.createAccountWithBalancePositive("123456","user1");
        accountUser2 = AccountFactory.createAccountWithBalancePositive("654321","user2");
        var transaction = Transaction.create(accountUser1.getAccountId(),accountUser2.getAccountId(),300.00,TransactionType.TRANSFER, null);
        BDDMockito.when(accountRepository.findById(accountUser1.getAccountId()))
                .thenReturn(Optional.of(AccountMapper.toEntity(accountUser1)));
        BDDMockito.when(accountRepository.findById(accountUser2.getAccountId()))
                .thenReturn(Optional.of(AccountMapper.toEntity(accountUser2)));
        BDDMockito.when(this.transactionRepository.save(ArgumentMatchers.any(TransactionEntity.class))).thenReturn(
                TransactionMapper.toEntity(transaction));
    }
    @Test
    void shouldTransferTransactionSuccessfully() {
        var transactionDto = new TransactionDto(accountUser1.getAccountId(), accountUser2.getAccountId(), 300.00, null);
        ArgumentCaptor<TransactionEntity> captor = ArgumentCaptor.forClass(TransactionEntity.class);
        transactionService.makeTransfer(transactionDto);
        Mockito.verify(transactionRepository).save(captor.capture());
        TransactionEntity savedTransaction = captor.getValue();
        assertEquals(accountUser1.getAccountId(), savedTransaction.getPayerId());
        assertEquals(accountUser2.getAccountId(), savedTransaction.getPayeeId());
        assertEquals(300.00, savedTransaction.getAmount());
        assertEquals(TransactionStatus.SUCCESSFUL, savedTransaction.getTransactionStatus());
        assertEquals(TransactionType.TRANSFER, savedTransaction.getTransactionType());
    }
    @Test
    void shouldDepositTransactionSuccessfully() {
        var transactionDto = new TransactionDto(null, accountUser2.getAccountId(), 300.00, null);
        ArgumentCaptor<TransactionEntity> captor = ArgumentCaptor.forClass(TransactionEntity.class);
        transactionService.makeDeposit(transactionDto);
        Mockito.verify(transactionRepository).save(captor.capture());
        TransactionEntity savedTransaction = captor.getValue();
        assertEquals(accountUser2.getAccountId(), savedTransaction.getPayeeId());
        assertEquals(300.00, savedTransaction.getAmount());
        assertEquals(TransactionStatus.SUCCESSFUL, savedTransaction.getTransactionStatus());
        assertEquals(TransactionType.DEPOSIT, savedTransaction.getTransactionType());
    }
    @Test
    void shouldWithdrawTransactionSuccessfully() {
        var transactionDto = new TransactionDto(accountUser1.getAccountId(), null, 300.00, null);
        ArgumentCaptor<TransactionEntity> captor = ArgumentCaptor.forClass(TransactionEntity.class);
        transactionService.makeWithdraw(transactionDto);
        Mockito.verify(transactionRepository).save(captor.capture());
        TransactionEntity savedTransaction = captor.getValue();
        assertEquals(accountUser1.getAccountId(), savedTransaction.getPayerId());
        assertEquals(300.00, savedTransaction.getAmount());
        assertEquals(TransactionStatus.SUCCESSFUL, savedTransaction.getTransactionStatus());
        assertEquals(TransactionType.WITHDRAW, savedTransaction.getTransactionType());
    }
    @Test
    void shouldPaymentTransactionSuccessfully() {
        var transactionDto = new TransactionDto(accountUser1.getAccountId(), null, 300.00, null);
        ArgumentCaptor<TransactionEntity> captor = ArgumentCaptor.forClass(TransactionEntity.class);
        transactionService.makePayment(transactionDto);
        Mockito.verify(transactionRepository).save(captor.capture());
        TransactionEntity savedTransaction = captor.getValue();
        assertEquals(accountUser1.getAccountId(), savedTransaction.getPayerId());
        assertEquals(300.00, savedTransaction.getAmount());
        assertEquals(TransactionStatus.SUCCESSFUL, savedTransaction.getTransactionStatus());
        assertEquals(TransactionType.PAYMENT, savedTransaction.getTransactionType());
    }
}
