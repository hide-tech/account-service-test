package com.test.yazykov.services;

import com.test.yazykov.dto.*;

public interface CustomerService {
    CustomerDetails register(SignIn signIn);

    CustomerDetails update(UpdateCustomer newCustomer);

    AccountDetails createNewAccount(AddAccountToCustomer addDto);

    CustomerDetails info(String idNumber);
}
