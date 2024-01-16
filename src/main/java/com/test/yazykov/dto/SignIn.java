package com.test.yazykov.dto;

import com.test.yazykov.domain.IdType;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record SignIn(
        @NotBlank(message = "Name should not be empty")
        String name,
        @Length(min = 12, max = 16, message = "Enter valid doc number")
        String idNumber,
        @NotBlank(message = "should be PASSPORT or DRIVER_LICENSE")
        IdType idType
) {
}
