package com.test.yazykov.dto;

import com.test.yazykov.domain.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public record AccountDetails(
        Long id,
        @NotNull
        BigDecimal value,
        @NotBlank
        String currency,
        Set<TransactionDetails> transactionsFrom,
        Set<TransactionDetails> transactionsTo
) {
        public static AccountDetails of(Account account) {
                return new AccountDetails(account.getId(), account.getValue(),
                        account.getCurrency(),
                        account.getTransactionsFrom().stream().map(TransactionDetails::of)
                                .collect(Collectors.toSet()),
                        account.getTransactionsTo().stream().map(TransactionDetails::of)
                                .collect(Collectors.toSet())
                        );
        }
}
