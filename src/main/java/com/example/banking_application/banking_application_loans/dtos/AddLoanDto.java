package com.example.banking_application.banking_application_loans.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AddLoanDto {

    private BigDecimal amount;
    private Long requesterId;
    private BigDecimal returnAmount;
    private String term;
    private BigDecimal monthlyPayment;
}
