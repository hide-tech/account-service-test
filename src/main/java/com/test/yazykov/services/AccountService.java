package com.test.yazykov.services;

import com.test.yazykov.dto.AccountDetails;
import com.test.yazykov.dto.Deposit;
import com.test.yazykov.dto.PayrollDetail;

public interface AccountService {

    AccountDetails transfer(PayrollDetail payroll, Long accountId);

    AccountDetails deposit(Deposit deposit, Long accountId);
}
