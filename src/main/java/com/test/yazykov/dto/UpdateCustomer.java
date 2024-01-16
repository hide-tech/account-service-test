package com.test.yazykov.dto;

import com.test.yazykov.domain.IdType;

public record UpdateCustomer(
        Long id,
        String name,
        String idNumber,
        IdType idType
) {
}
