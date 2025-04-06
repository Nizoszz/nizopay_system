package com.system.nizopay.core.service;

import com.system.nizopay.core.model.Account;
import com.system.nizopay.core.repository.AccountRepository;
import com.system.nizopay.persistence.orm.mapper.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountService{
    private final AccountRepository accountRepository;
    public void requestCredit(String accountId) {
        Account account = findAccountById(accountId);
        account.requestCredit();
        saveAccount(account);
    }

    public void approveCredit(String accountId, BigDecimal creditLimit) {
        Account account = findAccountById(accountId);
        account.approveCredit(creditLimit);
        saveAccount(account);
    }

    public void rejectCredit(String accountId) {
        Account account = findAccountById(accountId);
        account.rejectCredit();
        saveAccount(account);
    }
    private Account findAccountById(String accountId) {
        return accountRepository.findById(accountId)
                .map(AccountMapper::toModel)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    private void saveAccount(Account account) {
        accountRepository.save(AccountMapper.toEntity(account));
    }
}
