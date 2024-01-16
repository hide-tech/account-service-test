package com.test.yazykov.services;

import com.test.yazykov.domain.Transaction;
import com.test.yazykov.dto.AccountDetails;
import com.test.yazykov.dto.Deposit;
import com.test.yazykov.dto.PayrollDetail;
import com.test.yazykov.repositories.AccountRepo;
import com.test.yazykov.repositories.TransactionRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceHttp implements AccountService {

    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;

    @Transactional
    @Override
    public AccountDetails transfer(PayrollDetail payroll) {
        var from = accountRepo.findById(payroll.accountIdFrom()).orElseThrow(
                () -> new RuntimeException("Source account with this id wasn't found"));
        var to = accountRepo.findById(payroll.accountIdTo()).orElseThrow(
                () -> new RuntimeException("Destination account with this id wasn't found"));
        if (!payroll.currency().equals(from.getCurrency())) {
            var transactionFail = Transaction.createFail(from, to, payroll);
            transactionRepo.save(transactionFail);
            throw new RuntimeException("We support only national currency transfer yet");
        }
        if (from.getValue().compareTo(payroll.amount()) < 0) {
            var transactionFail = Transaction.createFail(from, to, payroll);
            transactionRepo.save(transactionFail);
            throw new RuntimeException("You have not enough balance on your account");
        }
        from.setValue(from.getValue().subtract(payroll.amount()));
        var newFrom = accountRepo.save(from);
        to.setValue(to.getValue().add(payroll.amount()));
        var transaction = Transaction.createOk(from, to, payroll);
        transactionRepo.save(transaction);
        return AccountDetails.of(newFrom);
    }

    @Transactional
    @Override
    public AccountDetails deposit(Deposit deposit) {
        var account = accountRepo.findById(deposit.accountId()).orElseThrow(
                () -> new RuntimeException("Account with this id wasn't found"));
        if (!account.getCurrency().equals(deposit.currency())) {
            throw new RuntimeException("We support only national currency transfer yet");
        }
        account.setValue(account.getValue().add(deposit.amount()));
        var acc = accountRepo.save(account);
        return AccountDetails.of(acc);
    }
}
