package com.vfaber.personalfinancemanager.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private long id;
    private String accountType;
    private float balance;
}
