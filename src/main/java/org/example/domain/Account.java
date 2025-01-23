package org.example.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Double availableBalance = 0.0;

    @ManyToOne
    @JoinColumn(name = "customer_number")
    private Customer customer;

}
