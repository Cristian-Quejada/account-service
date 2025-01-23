package org.example.service;

import org.example.domain.Account;
import org.example.domain.AccountType;
import org.example.domain.Customer;
import org.example.dto.AccountDto;
import org.example.dto.AccountResponse;
import org.example.dto.CreateAccountRequest;
import org.example.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final CustomerRepository customerRepository;

    public AccountService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public AccountResponse createAccount(CreateAccountRequest request) {
        Customer customer = new Customer();
        customer.setCustomerName(request.getCustomerName());
        customer.setCustomerMobile(request.getCustomerMobile());
        customer.setCustomerEmail(request.getCustomerEmail());
        customer.setAddress1(request.getAddress1());
        customer.setAddress2(request.getAddress2());

        Account account = new Account();
        account.setAccountType(request.getAccountType());
        account.setCustomer(customer);

        customer.setAccounts(Collections.singletonList(account));

        Customer savedCustomer = customerRepository.save(customer);

        return AccountResponse.builder()
                .customerNumber(savedCustomer.getCustomerNumber())
                .customerName(savedCustomer.getCustomerName())
                .customerMobile(savedCustomer.getCustomerMobile())
                .customerEmail(savedCustomer.getCustomerEmail())
                .address1(savedCustomer.getAddress1())
                .address2(savedCustomer.getAddress2())
                .savings(getAccountsByType(savedCustomer, AccountType.S))
                .checking(getAccountsByType(savedCustomer, AccountType.C))
                .transactionStatusCode(201)
                .transactionStatusDescription("Customer account created")
                .build();
    }

    public AccountResponse getCustomerAccount(Long customerNumber) {
        return customerRepository.findById(customerNumber)
                .map(customer -> AccountResponse.builder()
                        .customerNumber(customer.getCustomerNumber())
                        .customerName(customer.getCustomerName())
                        .customerMobile(customer.getCustomerMobile())
                        .customerEmail(customer.getCustomerEmail())
                        .address1(customer.getAddress1())
                        .address2(customer.getAddress2())
                        .savings(getAccountsByType(customer, AccountType.S))
                        .checking(getAccountsByType(customer, AccountType.C))
                        .transactionStatusCode(302)
                        .transactionStatusDescription("Customer Account found")
                        .build())
                .orElse(AccountResponse.builder()
                        .transactionStatusCode(401)
                        .transactionStatusDescription("Customer not found")
                        .build());
    }

    private List<AccountDto> getAccountsByType(Customer customer, AccountType type) {
        return customer.getAccounts().stream()
                .filter(account -> account.getAccountType() == type)
                .map(account -> AccountDto.builder()
                        .accountNumber(account.getAccountNumber())
                        .accountType(account.getAccountType().name())
                        .availableBalance(account.getAvailableBalance())
                        .build())
                .collect(Collectors.toList());
    }
}
