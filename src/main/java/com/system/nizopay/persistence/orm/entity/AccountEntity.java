package com.system.nizopay.persistence.orm.entity;
import com.system.nizopay.core.model.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_accounts")
@NoArgsConstructor
@AllArgsConstructor
@Data
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
    @Transient
    private List<TransactionEntity> transactions;
    @Transient
    private List<CardEntity> cards;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
