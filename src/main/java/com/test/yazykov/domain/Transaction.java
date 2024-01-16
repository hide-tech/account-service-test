package com.test.yazykov.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "from", nullable = false)
    private Account from;
    @ManyToOne
    @JoinColumn(name = "to", nullable = false)
    private Account to;
    private BigDecimal value;
    private String currency;
    @Enumerated(EnumType.STRING)
    private TransactionState state;
}
