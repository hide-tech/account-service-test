package com.test.yazykov.domain;

import com.test.yazykov.dto.SignIn;
import com.test.yazykov.dto.UpdateCustomer;
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

    public static Customer of(SignIn signIn) {
        return new Customer(null, signIn.name(), signIn.idNumber(),
                signIn.idType(), null);
    }

    public Customer update(UpdateCustomer update) {
        return new Customer(this.getId(), update.name(), update.idNumber(),
                update.idType(), this.getAccounts());
    }
}
