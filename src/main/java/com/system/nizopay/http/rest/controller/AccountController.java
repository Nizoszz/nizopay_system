package com.system.nizopay.http.rest.controller;

import com.system.nizopay.core.model.Card;
import com.system.nizopay.core.service.AccountService;
import com.system.nizopay.http.rest.dto.CreditCardDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController{
    private final AccountService accountService;
    @PutMapping("/{accountId}/request-credit")
    public ResponseEntity<String> requestCredit(@PathVariable String accountId) {
        accountService.requestCredit(accountId);
        return ResponseEntity.ok("Crédito solicitado com sucesso.");
    }
    @PutMapping("/{accountId}/approve-credit")
    public ResponseEntity<String> approvalCredit(@PathVariable String accountId, @RequestBody BigDecimal creditLimit) {
        accountService.approveCredit(accountId, creditLimit);
        return ResponseEntity.ok("Crédito aprovado com sucesso.");
    }
    @PutMapping("/{accountId}/reject-credit")
    public ResponseEntity<String> rejectCredit(@PathVariable String accountId){
        accountService.rejectCredit(accountId);
        return ResponseEntity.ok("Crédito reprovado.");
    }
}
