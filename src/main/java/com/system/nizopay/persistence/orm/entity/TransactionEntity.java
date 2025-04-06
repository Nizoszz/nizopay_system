package com.system.nizopay.persistence.orm.entity;
import com.system.nizopay.core.model.TransactionStatus;
import com.system.nizopay.core.model.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transactions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionEntity{
    @Id
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "payer_id", nullable = true)
    private String payerId;
    @Column(name = "payee_id", nullable = true)
    private String payeeId;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "transaction_status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
