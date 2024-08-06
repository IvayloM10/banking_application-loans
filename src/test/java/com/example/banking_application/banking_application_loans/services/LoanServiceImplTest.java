package com.example.banking_application.banking_application_loans.services;

import com.example.banking_application.banking_application_loans.dtos.AddLoanDto;
import com.example.banking_application.banking_application_loans.dtos.LoanDto;
import com.example.banking_application.banking_application_loans.entities.Loan;
import com.example.banking_application.banking_application_loans.repositories.LoanRepository;
import com.example.banking_application.banking_application_loans.services.impl.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoanServiceImplTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LoanServiceImpl loanService;

    private Loan loan;
    private LoanDto loanDto;
    private AddLoanDto addLoanDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        loan = new Loan();
        loan.setId(1L);
        loan.setAuthorized(false);
        loan.setAmount(new BigDecimal("1000"));
        loan.setReturnAmount(new BigDecimal("1100"));
        loan.setRequesterId(1L);
        loan.setRate(10.0);
        loan.setTerm("12 months");
        loan.setDate(LocalDate.now());
        loan.setMonthlyPayment(new BigDecimal("91.67"));
        loan.setStatus("Draft");

        loanDto = new LoanDto();
        loanDto.setId(1L);
        loanDto.setAuthorized(false);
        loanDto.setAmount(new BigDecimal("1000"));
        loanDto.setReturnAmount(new BigDecimal("1100"));
        loanDto.setRequesterId(1L);
        loanDto.setRate(10.0);
        loanDto.setTerm("12 months");
        loanDto.setDate(LocalDate.now());
        loanDto.setMonthlyPayment(new BigDecimal("91.67"));
        loanDto.setStatus("Draft");

        addLoanDto = new AddLoanDto();
        addLoanDto.setAmount(new BigDecimal("1000"));
        addLoanDto.setTerm("12 months");
        addLoanDto.setRequesterId(1L);
    }

    @Test
    public void testGetAllLoans() {
        when(loanRepository.findAll()).thenReturn(Arrays.asList(loan));
        when(modelMapper.map(loan, LoanDto.class)).thenReturn(loanDto);

        List<LoanDto> loanDtos = loanService.getAllLoans();

        assertNotNull(loanDtos);
        assertEquals(1, loanDtos.size());
        verify(loanRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(loan, LoanDto.class);
    }

    @Test
    public void testGetLoanById() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(modelMapper.map(loan, LoanDto.class)).thenReturn(loanDto);

        LoanDto foundLoanDto = loanService.getLoanById(1L);

        assertNotNull(foundLoanDto);
        assertEquals(loanDto, foundLoanDto);
        verify(loanRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(loan, LoanDto.class);
    }

    @Test
    public void testCreateLoan() {
        when(modelMapper.map(addLoanDto, Loan.class)).thenReturn(loan);
        when(loanRepository.save(loan)).thenReturn(loan);

        loanService.createLoan(addLoanDto);

        verify(modelMapper, times(1)).map(addLoanDto, Loan.class);
        verify(loanRepository, times(1)).save(loan);
        assertFalse(loan.isAuthorized());
        assertEquals("Draft", loan.getStatus());
        assertNotNull(loan.getDate());
    }

    @Test
    public void testDeleteLoan() {
        doNothing().when(loanRepository).deleteById(1L);

        loanService.deleteLoan(1L);

        verify(loanRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateLoan() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);
        when(modelMapper.map(loanDto, Loan.class)).thenReturn(loan);

        loanService.updateLoan(1L, loanDto);

        verify(loanRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(loanDto, Loan.class);
        verify(loanRepository, times(1)).save(loan);
    }
}
