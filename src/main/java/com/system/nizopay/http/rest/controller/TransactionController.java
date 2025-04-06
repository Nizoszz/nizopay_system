package com.system.nizopay.http.rest.controller;

import com.system.nizopay.core.model.Transaction;
import com.system.nizopay.core.service.TransactionService;
import com.system.nizopay.http.rest.dto.DepositDto;
import com.system.nizopay.http.rest.dto.PaymentDto;
import com.system.nizopay.http.rest.dto.TransferDto;
import com.system.nizopay.http.rest.dto.WithdrawDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class TransactionController{
    private final TransactionService transactionService;

    @PostMapping("{accountId}/transactions/make-transfer")
    public ResponseEntity<Transaction> makeTransfer(@PathVariable String accountId,@RequestBody TransferDto transferDto) {
        return ResponseEntity.ok(transactionService.makeTransfer(accountId,transferDto));
    }
    @PostMapping("{accountId}/transactions/make-withdraw")
    public ResponseEntity<Transaction> makeWithdraw(@PathVariable String accountId,@RequestBody WithdrawDto withdrawDto) {
        return ResponseEntity.ok(transactionService.makeWithdraw(accountId,withdrawDto));
    }
    @PostMapping("{accountId}/transactions/make-deposit")
    public ResponseEntity<Transaction> makeDeposit(@PathVariable String accountId,@RequestBody DepositDto depositDto) {
        return ResponseEntity.ok(transactionService.makeDeposit(accountId,depositDto));
    }
    @PostMapping("{accountId}/transactions/make-payment")
    public ResponseEntity<Transaction> makePayment(@PathVariable String accountId,@RequestBody PaymentDto paymentDto) {
        return ResponseEntity.ok(transactionService.makePayment(accountId,paymentDto));
    }

}
