package com.system.nizopay.persistence.orm.entity;

import com.system.nizopay.core.model.Card;
import com.system.nizopay.core.model.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.YearMonth;

@Entity
@Table(name = "tb_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity{
    @Id
    @Column(name = "card_id")
    private String cardId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity account;
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


    public static CardEntity toEntity(Card card, UserEntity user, AccountEntity account){
        return new CardEntity(card.getCardId(), user, account, card.getCardNumber(), card.getCardHolderName(), card.getCvv(), card.getExpirationDate(),
                              card.isCardActive(), card.getCardType());
    }

    public static Card toModel(CardEntity cardEntity){
        return new Card(cardEntity.getCardId(), cardEntity.getUser().getUserId(), cardEntity.getAccount().getAccountId(), cardEntity.getCardNumber(), cardEntity.getCardHolderName(),
                              cardEntity.getCvv(), cardEntity.getExpirationDate(), cardEntity.isCardActive(), cardEntity.getCardType());
    }
}
