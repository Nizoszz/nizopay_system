package com.system.nizopay.persistence.orm.entity;
import com.system.nizopay.core.model.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.YearMonth;

@Entity
@Table(name = "tb_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity {
    @Id
    @Column(name = "card_id")
    private String cardId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    private String cvv;

    @Column(name = "expiration_date")
    private YearMonth expirationDate;

    @Column(name = "is_card_active")
    private boolean isCardActive;

    @Enumerated(EnumType.STRING)
    @Column(name = "cart_type")
    private CardType cardType;
}

