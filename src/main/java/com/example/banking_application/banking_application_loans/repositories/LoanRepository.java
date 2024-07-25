package com.example.banking_application.banking_application_loans.repositories;

import com.example.banking_application.banking_application_loans.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {
}
