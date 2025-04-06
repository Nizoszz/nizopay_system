package com.system.nizopay.core.service;

import com.system.nizopay.core.exception.NotFoundException;
import com.system.nizopay.core.model.Card;
import com.system.nizopay.core.model.CreditCard;
import com.system.nizopay.core.repository.AccountRepository;
import com.system.nizopay.core.repository.CardRepository;
import com.system.nizopay.core.repository.UserRepository;
import com.system.nizopay.http.rest.dto.CreditCardDto;
import com.system.nizopay.persistence.orm.mapper.CardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@AllArgsConstructor
public class CardService{
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    public Card createCreditCard(String userId, CreditCardDto creditCardDto){
        var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        var account = accountRepository.findById(creditCardDto.accountId()).orElseThrow(() -> new NotFoundException("Account not found"));
        var card = CreditCard.create(user.getUserId(),creditCardDto.accountId(),creditCardDto.cardHolderName(), creditCardDto.creditLimit(), creditCardDto.currentBalance());
        var savedCardEntity = cardRepository.save(CardMapper.toEntity(card));
        return CardMapper.toModel(savedCardEntity);
    }
}
