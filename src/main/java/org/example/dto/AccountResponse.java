package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountResponse {

    private Long customerNumber;
    private String customerName;
    private String customerMobile;
    private String customerEmail;
    private String address1;
    private String address2;
    private List<AccountDto> savings;
    private List<AccountDto> checking;
    private int transactionStatusCode;
    private String transactionStatusDescription;
}
