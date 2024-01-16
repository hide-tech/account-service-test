package com.test.yazykov.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal value;
    private String currency;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "from")
    private Set<Transaction> transactionsFrom;
    @OneToMany(mappedBy = "to")
    private Set<Transaction> transactionsTo;

    public static Account createNewAcc(Customer customer) {
        return new Account(null, BigDecimal.ZERO, "BYN",
                customer, null, null);
    }
}
