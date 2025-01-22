package com.vfaber.personalfinancemanager.service.impl;

import com.vfaber.personalfinancemanager.entity.AccountEntity;
import com.vfaber.personalfinancemanager.entity.TransactionEntity;
import com.vfaber.personalfinancemanager.exceptions.AccountNotFoundException;
import com.vfaber.personalfinancemanager.exceptions.TransactionException;
import com.vfaber.personalfinancemanager.repository.AccountRepository;
import com.vfaber.personalfinancemanager.repository.TransactionRepository;
import com.vfaber.personalfinancemanager.service.TransferService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    @Override
    public void transfer(UUID accountFromId, UUID accountToId, double amount) {

        AccountEntity accountFrom = accountRepository.findById(accountFromId).orElseThrow(() -> new AccountNotFoundException(accountFromId.toString()));
        AccountEntity accountTo = accountRepository.findById(accountToId).orElseThrow(() -> new AccountNotFoundException(accountToId.toString()));

        if (accountFrom.getBalance() < amount) {
            throw new TransactionException("Insufficient funds in the source account");
        }
        if (amount <= 0) {
            throw new TransactionException("Transfer amount should be greater than zero");
        }

        accountFrom.setBalance(accountFrom.getBalance() - amount);
        accountTo.setBalance(accountTo.getBalance() + amount);

        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setAmount(amount);
        transaction.setAccountFrom(accountFrom);
        transaction.setAccountTo(accountTo);
        transactionRepository.save(transaction);
    }
}
