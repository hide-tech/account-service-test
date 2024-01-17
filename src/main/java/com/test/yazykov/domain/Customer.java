package com.test.yazykov.domain;

import com.test.yazykov.dto.SignIn;
import com.test.yazykov.dto.UpdateCustomer;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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

    public static Customer of(SignIn signIn) {
        return new Customer(null, signIn.name(), signIn.idNumber(),
                signIn.idType(), new HashSet<>());
    }

    public Customer update(UpdateCustomer update) {
        return new Customer(this.getId(), update.name(), update.idNumber(),
                update.idType(), this.getAccounts());
    }
}
