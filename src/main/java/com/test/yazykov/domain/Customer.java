package com.test.yazykov.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String idNumber;
    @Enumerated(EnumType.STRING)
    private IdType idType;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Account> accounts;
}
