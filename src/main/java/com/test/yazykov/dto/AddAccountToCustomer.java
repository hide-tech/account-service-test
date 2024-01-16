package com.test.yazykov.dto;

import jakarta.validation.constraints.NotNull;

public record AddAccountToCustomer(
        @NotNull
        Long customerId
) {
}
