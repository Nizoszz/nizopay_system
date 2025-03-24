package com.system.nizopay.persistence.orm.entity;

import com.system.nizopay.core.model.Card;
import com.system.nizopay.core.model.WalletStatus;
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
public class WalletEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private String walletId;
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
    @Column(name = "wallet_status")
    private WalletStatus walletStatus;
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<CardEntity> cards;
    private Date createdAt;
    private Date updatedAt;
    private Optional<Date> deletedAt;
}
