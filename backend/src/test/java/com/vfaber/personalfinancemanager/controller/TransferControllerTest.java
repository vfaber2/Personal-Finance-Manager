package com.vfaber.personalfinancemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vfaber.personalfinancemanager.dto.TransferRequestDto;
import com.vfaber.personalfinancemanager.entity.AccountEntity;
import com.vfaber.personalfinancemanager.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransferControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private AccountEntity accountFrom;
    private AccountEntity accountTo;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();

        // Initialize test accounts using Builder Pattern
        accountFrom = AccountEntity.builder()
                .uuid(UUID.randomUUID())
                .IBAN("DE12345678901234567890")
                .balance(1000.00)
                .build();
        accountFrom = accountRepository.save(accountFrom);

        accountTo = AccountEntity.builder()
                .uuid(UUID.randomUUID())
                .IBAN("DE09876543210987654321")
                .balance(500.00)
                .build();
        accountTo = accountRepository.save(accountTo);
    }

    @Test
    void testSuccessfulTransfer() throws Exception {
        // Arrange
        TransferRequestDto transferRequest = new TransferRequestDto();
        transferRequest.setAccountFromId(accountFrom.getUuid());
        transferRequest.setAccountToId(accountTo.getUuid());
        transferRequest.setAmount(200.00);

        // Act & Assert
        mockMvc.perform(post("/api/transaction/performTransaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testTransferInsufficientFunds() throws Exception {
        // Arrange
        TransferRequestDto transferRequest = new TransferRequestDto();
        transferRequest.setAccountFromId(accountFrom.getUuid());
        transferRequest.setAccountToId(accountTo.getUuid());
        transferRequest.setAmount(1200.00);

        // Act & Assert
        mockMvc.perform(post("/api/transaction/performTransaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isBadRequest());
    }

}
