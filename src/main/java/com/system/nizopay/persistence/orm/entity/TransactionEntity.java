package com.system.nizopay.persistence.orm.entity;

import com.system.nizopay.core.model.Transaction;
import com.system.nizopay.core.model.TransactionStatus;
import com.system.nizopay.core.model.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;


@Entity
@Table(name = "tb_transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TransactionEntity{
    @Id
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "payer_id")
    private String payerId;
    @Column(name = "payee_id")
    private String payeeId;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;
    @Column(name = "transaction_type")
    private TransactionType transactionType;
    @Column(name = "description", nullable = true)
    private Optional<String> description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public static TransactionEntity toEntity(Transaction data){
        return new TransactionEntity(data.getTransactionId(),
                                     data.getPayerId(),
                                     data.getPayeeId(),
                                     data.getAmount(),
                                     data.getTransactionStatus(),
                                     data.getTransactionType(),
                                     data.getDescription(),
                                     data.getCreatedAt());
    }

    public static Transaction toModel(TransactionEntity data){
        return new Transaction(
                data.getTransactionId(),
                data.getPayerId(),
                data.getPayeeId(),
                data.getAmount(),
                data.getTransactionStatus(),
                data.getTransactionType(),
                data.getDescription(),
                data.getCreatedAt()
        );
    }
}
