package com.test.yazykov.dto;

import com.test.yazykov.domain.Transaction;
import com.test.yazykov.domain.TransactionState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionDetails(
        Long id,
        @NotNull
        BigDecimal value,
        @NotBlank
        String currency,
        @NotBlank
        TransactionState state
) {
        public static TransactionDetails of(Transaction transaction) {
                return new TransactionDetails(transaction.getId(), transaction.getValue(),
                        transaction.getCurrency(), transaction.getState());
        }
}
