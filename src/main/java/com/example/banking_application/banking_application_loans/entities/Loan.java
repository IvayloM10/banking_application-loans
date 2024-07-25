package com.example.banking_application.banking_application_loans.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="loans")
@Getter
@Setter
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean isAuthorized;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal returnAmount;


    @Column(name = "requester_id")
    private Long requesterId;

    @Column
    private double rate;

    @Column
    private String term;

    @Column
    private LocalDate date;

    @Column
    private String loanUniqueIdentifier;

    @Column
    private BigDecimal monthlyPayment;

    @Column
    private String status;
}
