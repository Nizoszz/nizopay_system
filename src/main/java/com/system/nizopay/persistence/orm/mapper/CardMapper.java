package com.system.nizopay.persistence.orm.mapper;

import com.system.nizopay.core.model.Card;
import com.system.nizopay.persistence.orm.entity.AccountEntity;
import com.system.nizopay.persistence.orm.entity.CardEntity;

public class CardMapper{
    public static CardEntity toEntity(Card card){
        return new CardEntity(card.getCardId(),card.getUserId(),card.getAccountId(), card.getCardNumber(), card.getCardHolderName(), card.getCvv(), card.getExpirationDate(),
                              card.isCardActive(), card.getCardType());
    }

    public static Card toModel(CardEntity cardEntity){
        return new Card(cardEntity.getCardId(), cardEntity.getUserId(), cardEntity.getAccountId(), cardEntity.getCardNumber(), cardEntity.getCardHolderName(),
                        cardEntity.getCvv(), cardEntity.getExpirationDate(), cardEntity.isCardActive(), cardEntity.getCardType());
    }
}
