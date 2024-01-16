package com.test.yazykov.dto;

import java.math.BigDecimal;

public record Deposit(
        Long accountId,
        BigDecimal amount,
        String currency
) {
}
