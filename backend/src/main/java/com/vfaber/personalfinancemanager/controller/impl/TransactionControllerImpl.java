package com.vfaber.personalfinancemanager.controller.impl;

import com.vfaber.personalfinancemanager.controller.TransactionController;
import com.vfaber.personalfinancemanager.dto.TransactionDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public class TransactionControllerImpl implements TransactionController {
    @Override
    public ResponseEntity<TransactionDto> addTransaction(TransactionDto transactionDto) {
        return null;
    }

    @Override
    public ResponseEntity<TransactionDto> updateTransaction(TransactionDto transactionDto) {
        return null;
    }

    @Override
    public ResponseEntity<TransactionDto> deleteTransaction(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<TransactionDto> getTransactionById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        return null;
    }
}
