package com.system.nizopay.http.rest.controller;

import com.system.nizopay.core.model.Card;
import com.system.nizopay.http.rest.dto.CreditCardDto;
import com.system.nizopay.core.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class CardController{
    private final CardService cardService;

    @PostMapping("{userId}/credit-card")
    public ResponseEntity<Card> createCreditCard(@PathVariable String userId,@RequestBody CreditCardDto creditCardDto) {
        return ResponseEntity.ok(cardService.createCreditCard(userId, creditCardDto));
    }
}
