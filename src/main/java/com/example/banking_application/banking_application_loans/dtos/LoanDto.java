package com.example.banking_application.banking_application_loans.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LoanDto {
    private Long id;
    private boolean isAuthorized;
    private BigDecimal amount;
    private BigDecimal returnAmount;
    private Long requesterId;
    private double rate;
    private String term;
    private LocalDate date;
    private BigDecimal monthlyPayment;
    private String status;
}
