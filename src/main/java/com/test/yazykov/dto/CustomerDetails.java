package com.test.yazykov.dto;

import com.test.yazykov.domain.Customer;
import com.test.yazykov.domain.IdType;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
import java.util.stream.Collectors;

public record CustomerDetails(
        Long id,
        @NotBlank
        String name,
        @Length(min = 12, max = 16)
        String idNumber,
        @NotBlank
        IdType idType,
        Set<AccountDetails> accounts
) {
        public static CustomerDetails of(Customer customer) {
                return new CustomerDetails(customer.getId(), customer.getName(),
                        customer.getIdNumber(), customer.getIdType(),
                        customer.getAccounts().stream().map(AccountDetails::of)
                                .collect(Collectors.toSet()));
        }
}
