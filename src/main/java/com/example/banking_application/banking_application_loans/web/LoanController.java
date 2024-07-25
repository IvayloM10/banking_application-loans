package com.example.banking_application.banking_application_loans.web;


import com.example.banking_application.banking_application_loans.dtos.AddLoanDto;
import com.example.banking_application.banking_application_loans.dtos.LoanDto;
import com.example.banking_application.banking_application_loans.services.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/loans")
public class LoanController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);
    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanDto>> getAllLoans() {
        return ResponseEntity.ok(this.loanService.getAllLoans());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<LoanDto> getLoanById(@PathVariable Long id) {
        return ResponseEntity.ok(this.loanService.getLoanById(id));
    }

    @PostMapping
    public ResponseEntity<LoanDto> createLoan(@RequestBody AddLoanDto addLoanDto) {
        LOGGER.info("Going to create an offer {}",addLoanDto);
        this.loanService.createLoan(addLoanDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<LoanDto> updateLoan(@PathVariable Long id, @RequestBody LoanDto loanDto) {
        LOGGER.info("Going to update an offer {}",loanDto);
         this.loanService.updateLoan(id, loanDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<LoanDto> deleteLoan(@PathVariable Long id) {
        LOGGER.info("Going to delete an offer {}",id);
        this.loanService.deleteLoan(id);
        return ResponseEntity.ok().build();
    }
}
