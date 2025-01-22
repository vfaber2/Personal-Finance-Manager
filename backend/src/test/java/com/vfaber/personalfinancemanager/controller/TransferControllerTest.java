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

        accountFrom = new AccountEntity();
        accountFrom.setUuid(UUID.randomUUID());
        accountFrom.setIBAN("DE12345678901234567890");
        accountFrom.setBalance(1000.00);
        accountRepository.save(accountFrom);

        accountTo = new AccountEntity();
        accountTo.setUuid(UUID.randomUUID());
        accountTo.setIBAN("DE09876543210987654321");
        accountTo.setBalance(500.00);
        accountRepository.save(accountTo);
    }

    @Test
    void testSuccessfulTransfer() throws Exception {
        TransferRequestDto transferRequest = new TransferRequestDto();
        transferRequest.setAccountFromId(accountFrom.getUuid());
        transferRequest.setAccountToId(accountTo.getUuid());
        transferRequest.setAmount(200.00);

        mockMvc.perform(post("/api/transaction/performTransaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testTransferInsufficientFunds() throws Exception {
        TransferRequestDto transferRequest = new TransferRequestDto();
        transferRequest.setAccountFromId(accountFrom.getUuid());
        transferRequest.setAccountToId(accountTo.getUuid());
        transferRequest.setAmount(1200.00);

        mockMvc.perform(post("/api/transaction/performTransaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isBadRequest());
    }

}
