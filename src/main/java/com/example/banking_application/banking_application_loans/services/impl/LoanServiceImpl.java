package com.example.banking_application.banking_application_loans.services.impl;

import com.example.banking_application.banking_application_loans.dtos.AddLoanDto;
import com.example.banking_application.banking_application_loans.dtos.LoanDto;
import com.example.banking_application.banking_application_loans.entities.Loan;
import com.example.banking_application.banking_application_loans.repositories.LoanRepository;
import com.example.banking_application.banking_application_loans.services.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    private ModelMapper  modelMapper;

    public LoanServiceImpl(LoanRepository loanRepository, ModelMapper modelMapper) {
        this.loanRepository = loanRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<LoanDto> getAllLoans() {

        return this.loanRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public LoanDto getLoanById(Long id) {
        return this.loanRepository
                .findById(id)
                .map(this::map)
                .orElseThrow(() -> new IllegalArgumentException("Not found!"));
    }

    @Override
    public void createLoan(AddLoanDto addLoanDto) {
        Loan mappedLoan = map(addLoanDto);
        mappedLoan.setAuthorized(false);
        mappedLoan.setRate(Double.parseDouble(String.valueOf(mappedLoan.getReturnAmount().divide(mappedLoan.getAmount()))));
        mappedLoan.setStatus("Draft");
        mappedLoan.setDate( LocalDate.now());
        this.loanRepository.save(mappedLoan);
    }

    @Override
    public void deleteLoan(Long id) {
        System.out.println("delete");
        this.loanRepository.deleteById(id);
    }

    @Override
    public void updateLoan(Long id, LoanDto loanDto) {
        System.out.println("update");
        Loan loan = this.loanRepository.findById(id).orElse(null);
        loan = this.modelMapper.map(loanDto,Loan.class);
        this.loanRepository.save(loan);
    }


    private LoanDto map(Loan loan) {
        return this.modelMapper.map(loan,LoanDto.class);
    }


    private Loan map(AddLoanDto addLoanDto) {
        Loan loan = this.modelMapper.map(addLoanDto, Loan.class);
        //implement so not to override a loan at id 1
        loan.setId(null);
        return loan;
    }
}

