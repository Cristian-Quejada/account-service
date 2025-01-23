package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {

    private Long accountNumber;
    private String accountType;
    private Double availableBalance;
}
