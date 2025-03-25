package com.system.nizopay.persistence.orm.entity;

import com.system.nizopay.core.model.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "tb_wallets")
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
    private Date createdAt;
    private Date updatedAt;
    private Optional<Date> deletedAt;
}
