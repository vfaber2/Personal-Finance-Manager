package com.vfaber.personalfinancemanager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDTO {
    private long id;
    private Date date;
    private float amount;
    private String type;
}
