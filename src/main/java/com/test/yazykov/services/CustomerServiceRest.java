package com.test.yazykov.services;

import com.test.yazykov.domain.Account;
import com.test.yazykov.domain.Customer;
import com.test.yazykov.dto.*;
import com.test.yazykov.repositories.AccountRepo;
import com.test.yazykov.repositories.CustomerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceRest implements CustomerService {

    private final CustomerRepo customerRepo;
    private final AccountRepo accountRepo;

    @Override
    public CustomerDetails register(SignIn signIn) {
        var customer = Customer.of(signIn);
        var saved = customerRepo.save(customer);
        return CustomerDetails.of(saved);
    }

    @Transactional
    @Override
    public CustomerDetails update(UpdateCustomer newCustomer, Long customerId) {
        var old = customerRepo.findById(customerId).orElseThrow(
                () -> new RuntimeException("Customer with this id wasn't found"));
        var updated = old.update(newCustomer);
        var saved = customerRepo.save(updated);
        return CustomerDetails.of(saved);
    }

    @Transactional
    @Override
    public AccountDetails createNewAccount(Long customerId) {
        var customer = customerRepo.findById(customerId).orElseThrow(
                () -> new RuntimeException("Customer with this id wasn't found"));
        var account = Account.createNewAcc(customer);
        var savedAcc = accountRepo.save(account);
        return AccountDetails.of(savedAcc);
    }

    @Override
    public CustomerDetails info(String idNumber) {
        var customer = customerRepo.findByIdNumber(idNumber).orElseThrow(
                () -> new RuntimeException("Customer with this idNumber wasn't found"));
        return CustomerDetails.of(customer);
    }
}
