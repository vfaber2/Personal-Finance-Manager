package com.vfaber.personalfinancemanager.controller;

import com.vfaber.personalfinancemanager.dto.TransactionDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TransactionController {
    ResponseEntity<TransactionDto> addTransaction(TransactionDto transactionDto);

    ResponseEntity<TransactionDto> updateTransaction(TransactionDto transactionDto);

    ResponseEntity<TransactionDto> deleteTransaction(UUID id);

    ResponseEntity<TransactionDto> getTransactionById(UUID id);

    ResponseEntity<List<TransactionDto>> getAllTransactions();

}
