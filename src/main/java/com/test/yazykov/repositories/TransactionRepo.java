package com.test.yazykov.repositories;

import com.test.yazykov.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}
