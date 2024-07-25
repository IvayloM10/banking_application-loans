package com.example.banking_application.banking_application_loans.services;

import com.example.banking_application.banking_application_loans.dtos.AddLoanDto;
import com.example.banking_application.banking_application_loans.dtos.LoanDto;

import java.util.List;

public interface LoanService {
    List<LoanDto> getAllLoans();

    LoanDto getLoanById(Long id);

    void createLoan(AddLoanDto addLoanDto);

    void deleteLoan(Long id);

    void updateLoan(Long id, LoanDto loanDto);
}
