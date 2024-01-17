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
    public CustomerDetails register(@RequestBody SignIn signIn) {
        return customerService.register(signIn);
    }

    @PutMapping("/app/v1/customers/{customerId}")
    public CustomerDetails update(@PathVariable("customerId") Long customerId,
                                                  @RequestBody UpdateCustomer update) {
        return customerService.update(update, customerId);
    }

    @PostMapping("/app/v1/customers/{customerId}/accounts")
    public AccountDetails createNewAcc(
            @PathVariable("customerId") Long customerId) {
        return customerService.createNewAccount(customerId);
    }

    @GetMapping("/app/v1/customers")
    public CustomerDetails getCustomerByIdNumber(
            @RequestParam("idnum") String idNumber) {
        return customerService.info(idNumber);
    }
}
