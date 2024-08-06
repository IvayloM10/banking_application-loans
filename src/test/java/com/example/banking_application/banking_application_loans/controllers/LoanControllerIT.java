package com.example.banking_application.banking_application_loans.controllers;
import com.example.banking_application.banking_application_loans.dtos.AddLoanDto;
import com.example.banking_application.banking_application_loans.dtos.LoanDto;
import com.example.banking_application.banking_application_loans.services.LoanService;
import com.example.banking_application.banking_application_loans.web.LoanController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanController.class)
@AutoConfigureMockMvc
public class LoanControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    private LoanDto loanDto1;
    private LoanDto loanDto2;
    private AddLoanDto addLoanDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();

        // Initialize LoanDto instances
        loanDto1 = new LoanDto();
        loanDto1.setId(1L);
        loanDto1.setAuthorized(true);
        loanDto1.setAmount(new BigDecimal("1000.00"));
        loanDto1.setReturnAmount(new BigDecimal("1100.00"));
        loanDto1.setRequesterId(1L);
        loanDto1.setRate(5.0);
        loanDto1.setTerm("12 months");
        loanDto1.setDate(LocalDate.now());
        loanDto1.setMonthlyPayment(new BigDecimal("91.67"));
        loanDto1.setStatus("APPROVED");

        loanDto2 = new LoanDto();
        loanDto2.setId(2L);
        loanDto2.setAuthorized(false);
        loanDto2.setAmount(new BigDecimal("2000.00"));
        loanDto2.setReturnAmount(new BigDecimal("2200.00"));
        loanDto2.setRequesterId(2L);
        loanDto2.setRate(4.0);
        loanDto2.setTerm("24 months");
        loanDto2.setDate(LocalDate.now());
        loanDto2.setMonthlyPayment(new BigDecimal("91.67"));
        loanDto2.setStatus("PENDING");

        addLoanDto = new AddLoanDto();
        addLoanDto.setAmount(new BigDecimal("1000.00"));
        addLoanDto.setTerm("12 months");
        addLoanDto.setRequesterId(1L);
    }

    @Test
    public void testGetAllLoans() throws Exception {
        List<LoanDto> loans = Arrays.asList(loanDto1, loanDto2);

        Mockito.when(loanService.getAllLoans()).thenReturn(loans);

        mockMvc.perform(get("/loans"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
//                .andExpect(jsonPath("$[0].isAuthorized").value(true))
                .andExpect(jsonPath("$[0].amount").value(1000.00))
                .andExpect(jsonPath("$[0].returnAmount").value(1100.00))
                .andExpect(jsonPath("$[0].requesterId").value(1L))
                .andExpect(jsonPath("$[0].rate").value(5.0))
                .andExpect(jsonPath("$[0].term").value("12 months"))
                .andExpect(jsonPath("$[0].date").exists())
                .andExpect(jsonPath("$[0].monthlyPayment").value(91.67))
                .andExpect(jsonPath("$[0].status").value("APPROVED"))
                .andExpect(jsonPath("$[1].id").value(2L))
                //.andExpect(jsonPath("$[1].isAuthorized").value(false))
                .andExpect(jsonPath("$[1].amount").value(2000.00))
                .andExpect(jsonPath("$[1].returnAmount").value(2200.00))
                .andExpect(jsonPath("$[1].requesterId").value(2L))
                .andExpect(jsonPath("$[1].rate").value(4.0))
                .andExpect(jsonPath("$[1].term").value("24 months"))
                .andExpect(jsonPath("$[1].date").exists())
                .andExpect(jsonPath("$[1].monthlyPayment").value(91.67))
                .andExpect(jsonPath("$[1].status").value("PENDING"));
    }

    @Test
    public void testGetLoanById() throws Exception {
        Mockito.when(loanService.getLoanById(1L)).thenReturn(loanDto1);

        mockMvc.perform(get("/loans/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                //.andExpect(jsonPath("$.isAuthorized").value(true))
                .andExpect(jsonPath("$.amount").value(1000.00))
                .andExpect(jsonPath("$.returnAmount").value(1100.00))
                .andExpect(jsonPath("$.requesterId").value(1L))
                .andExpect(jsonPath("$.rate").value(5.0))
                .andExpect(jsonPath("$.term").value("12 months"))
                .andExpect(jsonPath("$.date").exists())
                .andExpect(jsonPath("$.monthlyPayment").value(91.67))
                .andExpect(jsonPath("$.status").value("APPROVED"));
    }

    @Test
    public void testCreateLoan() throws Exception {
        Mockito.doNothing().when(loanService).createLoan(Mockito.any(AddLoanDto.class));

        mockMvc.perform(post("/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":1000.00,\"rate\":5.0,\"term\":\"12 months\",\"requesterId\":1}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateLoan() throws Exception {
        Mockito.doNothing().when(loanService).updateLoan(Mockito.anyLong(), Mockito.any(LoanDto.class));

        mockMvc.perform(put("/loans/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"isAuthorized\":true,\"amount\":1500.00,\"returnAmount\":1650.00,\"requesterId\":1,\"rate\":4.5,\"term\":\"18 months\",\"date\":\"2023-08-06\",\"monthlyPayment\":91.67,\"status\":\"APPROVED\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteLoan() throws Exception {
        Mockito.doNothing().when(loanService).deleteLoan(Mockito.anyLong());

        mockMvc.perform(delete("/loans/1"))
                .andExpect(status().isOk());
    }
}
