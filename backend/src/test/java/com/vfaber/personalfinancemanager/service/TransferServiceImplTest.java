package com.vfaber.personalfinancemanager.service;

import com.vfaber.personalfinancemanager.entity.AccountEntity;
import com.vfaber.personalfinancemanager.entity.TransactionEntity;
import com.vfaber.personalfinancemanager.exceptions.AccountNotFoundException;
import com.vfaber.personalfinancemanager.exceptions.TransactionException;
import com.vfaber.personalfinancemanager.repository.AccountRepository;
import com.vfaber.personalfinancemanager.repository.TransactionRepository;
import com.vfaber.personalfinancemanager.service.impl.TransferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransferServiceImpl transferService;

    private AccountEntity accountFrom;
    private AccountEntity accountTo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test accounts
        accountFrom = new AccountEntity();
        accountFrom.setUuid(UUID.randomUUID());
        accountFrom.setIBAN("DE12345678901234567890");
        accountFrom.setBalance(1000.00);

        accountTo = new AccountEntity();
        accountTo.setUuid(UUID.randomUUID());
        accountTo.setIBAN("DE09876543210987654321");
        accountTo.setBalance(500.00);
    }

    @Test
    void testSuccessfulTransfer() {
        // Arrange
        double transferAmount = 200.00;

        when(accountRepository.findById(accountFrom.getUuid())).thenReturn(Optional.of(accountFrom));
        when(accountRepository.findById(accountTo.getUuid())).thenReturn(Optional.of(accountTo));

        // Act
        transferService.transfer(accountFrom.getUuid(), accountTo.getUuid(), transferAmount);

        // Assert
        assertEquals(800.00, accountFrom.getBalance());
        assertEquals(700.00, accountTo.getBalance());
        verify(accountRepository).save(accountFrom);
        verify(accountRepository).save(accountTo);

        // Verify transaction is saved
        ArgumentCaptor<TransactionEntity> transactionCaptor = ArgumentCaptor.forClass(TransactionEntity.class);
        verify(transactionRepository).save(transactionCaptor.capture());
        TransactionEntity savedTransaction = transactionCaptor.getValue();
        assertEquals(transferAmount, savedTransaction.getAmount());
        assertEquals(accountFrom, savedTransaction.getAccountFrom());
        assertEquals(accountTo, savedTransaction.getAccountTo());
    }

    @Test
    void testTransferInsufficientFunds() {
        // Arrange
        double transferAmount = 1200.00;

        when(accountRepository.findById(accountFrom.getUuid())).thenReturn(Optional.of(accountFrom));
        when(accountRepository.findById(accountTo.getUuid())).thenReturn(Optional.of(accountTo));

        // Act & Assert
        TransactionException exception = assertThrows(TransactionException.class, () ->
                transferService.transfer(accountFrom.getUuid(), accountTo.getUuid(), transferAmount)
        );
        assertEquals("Insufficient funds in the source account", exception.getMessage());
        verify(accountRepository, never()).save(any(AccountEntity.class));
        verify(transactionRepository, never()).save(any(TransactionEntity.class));
    }

    @Test
    void testTransferInvalidAmount() {
        // Arrange
        double transferAmount = -100.00;

        when(accountRepository.findById(accountFrom.getUuid())).thenReturn(Optional.of(accountFrom));
        when(accountRepository.findById(accountTo.getUuid())).thenReturn(Optional.of(accountTo));

        // Act & Assert
        TransactionException exception = assertThrows(TransactionException.class, () ->
                transferService.transfer(accountFrom.getUuid(), accountTo.getUuid(), transferAmount)
        );
        assertEquals("Transfer amount should be greater than zero", exception.getMessage());
        verify(accountRepository, never()).save(any(AccountEntity.class));
        verify(transactionRepository, never()).save(any(TransactionEntity.class));
    }

    @Test
    void testTransferAccountNotFound() {
        // Arrange
        when(accountRepository.findById(accountFrom.getUuid())).thenReturn(Optional.empty());

        // Act & Assert
        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class, () ->
                transferService.transfer(accountFrom.getUuid(), accountTo.getUuid(), 200.00)
        );
        assertEquals("Account:" + accountFrom.getUuid() + " not found", exception.getMessage());
        verify(accountRepository, never()).save(any(AccountEntity.class));
        verify(transactionRepository, never()).save(any(TransactionEntity.class));
    }
}
