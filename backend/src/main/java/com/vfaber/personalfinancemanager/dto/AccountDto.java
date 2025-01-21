package com.vfaber.personalfinancemanager.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountDto {
    private UUID id;
    private String IBAN;
    private UserDto accountHolder;
    private double balance;
}
