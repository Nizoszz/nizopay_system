package com.system.nizopay.service;

import com.system.nizopay.core.model.Transaction;
import com.system.nizopay.core.model.TransactionType;
import com.system.nizopay.core.model.Account;
import com.system.nizopay.core.repository.TransactionRepository;
import com.system.nizopay.core.service.TransactionService;
import com.system.nizopay.persistence.orm.entity.TransactionEntity;
import com.system.nizopay.util.AccountFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTests{
    @InjectMocks
    private TransactionService transactionService;
    @Mock
    private TransactionRepository transactionRepository;
    private Account accountUser1;
    private Account accountUser2;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        accountUser1 = AccountFactory.createAccountWithBalancePositive("123456","user1");
        accountUser2 = AccountFactory.createAccountWithBalancePositive("654321","user2");
        transaction = Transaction.create(accountUser1.getUserId(),accountUser2.getUserId(),300.00,
                                         TransactionType.PAYMENT,java.util.Optional.empty());
        BDDMockito.when(this.transactionRepository.save(ArgumentMatchers.any(TransactionEntity.class))).thenReturn(TransactionEntity.toEntity(transaction));
    }
}
