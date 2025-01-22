package com.vfaber.personalfinancemanager.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TransferRequestDto {
    private UUID accountFromId;
    private UUID accountToId;
    private double amount;
}
