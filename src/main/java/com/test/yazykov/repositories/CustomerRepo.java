package com.test.yazykov.repositories;

import com.test.yazykov.domain.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @EntityGraph(attributePaths = {"accounts",
            "accounts.transactionsFrom",
            "accounts.transactionsTo"})
    Optional<Customer> findByIdNumber(Long idNumber);
}
