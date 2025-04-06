package com.system.nizopay.core.service;

import com.system.nizopay.core.model.*;
import com.system.nizopay.core.repository.AccountRepository;
import com.system.nizopay.core.repository.TransactionRepository;
import com.system.nizopay.http.rest.dto.DepositDto;
import com.system.nizopay.http.rest.dto.PaymentDto;
import com.system.nizopay.http.rest.dto.TransferDto;
import com.system.nizopay.http.rest.dto.WithdrawDto;
import com.system.nizopay.persistence.orm.entity.TransactionEntity;
import com.system.nizopay.persistence.orm.mapper.AccountMapper;
import com.system.nizopay.persistence.orm.mapper.TransactionMapper;
import com.system.nizopay.util.AccountFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
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
        accountUser1 = AccountFactory.createAccountWithBalancePositive("user1");
        accountUser2 = AccountFactory.createAccountWithBalancePositive("user2");
        var transaction = Transaction.create(accountUser1.getAccountId(),accountUser2.getAccountId(),BigDecimal.valueOf(300.00),TransactionType.TRANSFER, null);
        BDDMockito.when(accountRepository.findById(accountUser1.getAccountId()))
                .thenReturn(Optional.of(AccountMapper.toEntity(accountUser1)));
        BDDMockito.when(accountRepository.findById(accountUser2.getAccountId()))
                .thenReturn(Optional.of(AccountMapper.toEntity(accountUser2)));
        BDDMockito.when(this.transactionRepository.save(ArgumentMatchers.any(TransactionEntity.class))).thenReturn(
                TransactionMapper.toEntity(transaction));
    }
    @Test
    void shouldTransferTransactionSuccessfully() {
        var transactionDto = new TransferDto(accountUser2.getAccountId(),BigDecimal.valueOf(300.00),null);
        ArgumentCaptor<TransactionEntity> captor = ArgumentCaptor.forClass(TransactionEntity.class);
        transactionService.makeTransfer(accountUser1.getAccountId(),transactionDto);
        Mockito.verify(transactionRepository).save(captor.capture());
        TransactionEntity savedTransaction = captor.getValue();
        assertEquals(accountUser1.getAccountId(), savedTransaction.getPayerId());
        assertEquals(accountUser2.getAccountId(), savedTransaction.getPayeeId());
        assertEquals(BigDecimal.valueOf(300.00), savedTransaction.getAmount());
        assertEquals(TransactionStatus.SUCCESSFUL, savedTransaction.getTransactionStatus());
        assertEquals(TransactionType.TRANSFER, savedTransaction.getTransactionType());
    }
    @Test
    void shouldDepositTransactionSuccessfully() {
        var transactionDto = new DepositDto(BigDecimal.valueOf(300.00),null);
        ArgumentCaptor<TransactionEntity> captor = ArgumentCaptor.forClass(TransactionEntity.class);
        transactionService.makeDeposit(accountUser2.getAccountId(),transactionDto);
        Mockito.verify(transactionRepository).save(captor.capture());
        TransactionEntity savedTransaction = captor.getValue();
        assertEquals(accountUser2.getAccountId(), savedTransaction.getPayeeId());
        assertEquals(BigDecimal.valueOf(300.00), savedTransaction.getAmount());
        assertEquals(TransactionStatus.SUCCESSFUL, savedTransaction.getTransactionStatus());
        assertEquals(TransactionType.DEPOSIT, savedTransaction.getTransactionType());
    }
    @Test
    void shouldWithdrawTransactionSuccessfully() {
        var transactionDto = new WithdrawDto(BigDecimal.valueOf(300.00),null);
        ArgumentCaptor<TransactionEntity> captor = ArgumentCaptor.forClass(TransactionEntity.class);
        transactionService.makeWithdraw(accountUser1.getAccountId(),transactionDto);
        Mockito.verify(transactionRepository).save(captor.capture());
        TransactionEntity savedTransaction = captor.getValue();
        assertEquals(accountUser1.getAccountId(), savedTransaction.getPayerId());
        assertEquals(BigDecimal.valueOf(300.00), savedTransaction.getAmount());
        assertEquals(TransactionStatus.SUCCESSFUL, savedTransaction.getTransactionStatus());
        assertEquals(TransactionType.WITHDRAW, savedTransaction.getTransactionType());
    }
    @Test
    void shouldPaymentTransactionSuccessfully() {
        var transactionDto = new PaymentDto(BigDecimal.valueOf(300.00),null);
        ArgumentCaptor<TransactionEntity> captor = ArgumentCaptor.forClass(TransactionEntity.class);
        transactionService.makePayment(accountUser1.getAccountId(), transactionDto);
        Mockito.verify(transactionRepository).save(captor.capture());
        TransactionEntity savedTransaction = captor.getValue();
        assertEquals(accountUser1.getAccountId(), savedTransaction.getPayerId());
        assertEquals(BigDecimal.valueOf(300.00), savedTransaction.getAmount());
        assertEquals(TransactionStatus.SUCCESSFUL, savedTransaction.getTransactionStatus());
        assertEquals(TransactionType.PAYMENT, savedTransaction.getTransactionType());
    }
}
