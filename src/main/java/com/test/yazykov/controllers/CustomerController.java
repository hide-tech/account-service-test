package com.test.yazykov.controllers;

import com.test.yazykov.dto.AccountDetails;
import com.test.yazykov.dto.CustomerDetails;
import com.test.yazykov.dto.SignIn;
import com.test.yazykov.dto.UpdateCustomer;
import com.test.yazykov.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/app/v1/customers")
    public ResponseEntity<CustomerDetails> register(@RequestBody SignIn signIn) {
        return ResponseEntity.ok(customerService.register(signIn));
    }

    @PutMapping("/app/v1/customers/{customerId}")
    public ResponseEntity<CustomerDetails> update(@PathVariable("customerId") Long customerId,
                                                  @RequestBody UpdateCustomer update) {
        return ResponseEntity.ok(customerService.update(update));
    }

    @PostMapping("/app/v1/customers/{customerId}/accounts")
    public ResponseEntity<AccountDetails> createNewAcc(
            @PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(customerService.createNewAccount(customerId));
    }

    @GetMapping("/app/v1/customers")
    public ResponseEntity<CustomerDetails> getCustomerByIdNumber(
            @RequestParam("idnum") String idNumber) {
        return ResponseEntity.ok(customerService.info(idNumber));
    }
}
