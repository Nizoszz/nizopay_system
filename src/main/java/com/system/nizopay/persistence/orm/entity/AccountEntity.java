package com.system.nizopay.persistence.orm.entity;

import com.system.nizopay.core.model.Account;
import com.system.nizopay.core.model.AccountStatus;
import com.system.nizopay.core.model.Card;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountEntity{
    @Id
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "account_number")
    private String accountNumber;
    private String agency;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "is_active")
    private boolean isActive;
    private BigDecimal balance;
    @Column(name = "credit_limit")
    private BigDecimal creditLimit;
    @Column(name = "account_status")
    private AccountStatus accountStatus;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<CardEntity> cards;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(nullable = true, name = "deleted_at")
    private LocalDateTime deletedAt;

}
