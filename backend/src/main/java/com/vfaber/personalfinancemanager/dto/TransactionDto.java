package com.vfaber.personalfinancemanager.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TransactionDto {
    private UUID transactionId;
    private double amount;
    private AccountDto accountFrom;
    private AccountDto accountTo;
}
