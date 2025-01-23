package org.example.dto;

import lombok.Data;
import org.example.domain.AccountType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateAccountRequest {

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Mobile number is required")
    private String customerMobile;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String customerEmail;

    @NotBlank(message = "Address is required")
    private String address1;

    private String address2;

    @NotNull(message = "Account type is required")
    private AccountType accountType;
}
