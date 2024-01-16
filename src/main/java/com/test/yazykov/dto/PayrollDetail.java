package com.test.yazykov.dto;

import java.math.BigDecimal;

public record PayrollDetail(
        Long accountIdFrom,
        Long accountIdTo,
        BigDecimal amount,
        String currency
) {
}
