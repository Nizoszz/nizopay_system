package com.system.nizopay.persistence.orm.mapper;

import com.system.nizopay.core.model.Account;
import com.system.nizopay.core.model.Card;
import com.system.nizopay.persistence.orm.entity.AccountEntity;
import com.system.nizopay.persistence.orm.entity.CardEntity;
import com.system.nizopay.persistence.orm.entity.TransactionEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper{
    public static AccountEntity toEntity(Account account) {
        return new AccountEntity(
                account.getAccountId(),
                account.getAccountNumber(),
                account.getAgency(),
                account.getUserId(),
                account.isActive(),
                account.getBalance(),
                account.getCreditLimit(),
                account.getAccountStatus(),
                account.getTransactions() != null
                        ? account.getTransactions().stream().map(TransactionMapper::toEntity).collect(Collectors.toList())
                        : List.of(),
                account.getCards() != null
                        ? account.getCards().stream().map(CardMapper::toEntity).collect(Collectors.toList())
                        : List.of(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }
    public static Account toModel(AccountEntity accountEntity) {
        return Account.restore(
                accountEntity.getAccountId(),
                accountEntity.getAccountNumber(),
                accountEntity.getAgency(),
                accountEntity.getUserId(),
                accountEntity.isActive(),
                accountEntity.getBalance(),
                accountEntity.getCreditLimit(),
                accountEntity.getAccountStatus(),
                accountEntity.getTransactions() != null
                        ? accountEntity.getTransactions().stream().map(TransactionMapper::toModel).collect(Collectors.toList())
                        : List.of(),
                accountEntity.getCards() != null
                        ? accountEntity.getCards().stream().map(CardMapper::toModel).collect(Collectors.toList())
                        : List.of(),
                accountEntity.getCreatedAt(),
                accountEntity.getUpdatedAt(),
                accountEntity.getDeletedAt()
        );
    }
}
