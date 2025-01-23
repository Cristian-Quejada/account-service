package org.example.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerNumber;

    @Column(length = 50, nullable = false)
    private String customerName;

    @Column(length = 20, nullable = false)
    private String customerMobile;

    @Column(length = 50, nullable = false)
    private String customerEmail;

    @Column(length = 100, nullable = false)
    private String address1;

    @Column(length = 100)
    private String address2;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;
}
