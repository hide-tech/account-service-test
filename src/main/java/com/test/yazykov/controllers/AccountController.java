package com.test.yazykov.controllers;

import com.test.yazykov.dto.AccountDetails;
import com.test.yazykov.dto.Deposit;
import com.test.yazykov.dto.PayrollDetail;
import com.test.yazykov.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/app/v1/customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDetails> transfer(@RequestBody PayrollDetail payrollDetail,
                                                   @PathVariable("accountId") Long accountId,
                                                   @PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(accountService.transfer(payrollDetail, accountId));
    }

    @PostMapping("/app/v1/customers/{customerId}/accounts/{accountId}/deposit")
    public ResponseEntity<AccountDetails> deposit(@RequestBody Deposit deposit,
                                                  @PathVariable("accountId") Long accountId) {
        return ResponseEntity.ok(accountService.deposit(deposit, accountId));
    }
}
