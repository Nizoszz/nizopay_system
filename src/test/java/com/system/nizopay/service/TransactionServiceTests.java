package com.system.nizopay.service;

import com.system.nizopay.core.model.Transaction;
import com.system.nizopay.core.model.TransactionType;
import com.system.nizopay.core.model.Wallet;
import com.system.nizopay.core.repository.TransactionRepository;
import com.system.nizopay.core.service.TransactionService;
import com.system.nizopay.persistence.orm.entity.TransactionEntity;
import com.system.nizopay.util.WalletFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTests{
    @InjectMocks
    private TransactionService transactionService;
    @Mock
    private TransactionRepository transactionRepository;
    private Wallet walletUser1;
    private Wallet walletUser2;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        walletUser1 = WalletFactory.createWalletWithBalancePositive("123456", "user1");
        walletUser2 = WalletFactory.createWalletWithBalancePositive("654321", "user2");
        transaction = Transaction.create(walletUser1.getUserId(),walletUser2.getUserId(),300.00,
                                         TransactionType.IN,java.util.Optional.empty());
        BDDMockito.when(this.transactionRepository.save(ArgumentMatchers.any(TransactionEntity.class))).thenReturn(TransactionEntity.toEntity(transaction));
    }
}
