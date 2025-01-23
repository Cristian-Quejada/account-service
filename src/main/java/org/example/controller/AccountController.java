package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AccountResponse;
import org.example.dto.CreateAccountRequest;
import org.example.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        AccountResponse response = accountService.createAccount(request);
        return ResponseEntity.status(response.getTransactionStatusCode()).body(response);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<AccountResponse> getCustomerAccount(@PathVariable Long customerNumber) {
        AccountResponse response = accountService.getCustomerAccount(customerNumber);
        return ResponseEntity.status(response.getTransactionStatusCode()).body(response);
    }
}
