package com.test.yazykov.services;

import com.test.yazykov.dto.*;

public interface CustomerService {
    CustomerDetails register(SignIn signIn);

    CustomerDetails update(UpdateCustomer newCustomer, Long customerId);

    AccountDetails createNewAccount(Long customerId);

    CustomerDetails info(String idNumber);
}
