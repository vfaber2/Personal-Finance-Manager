package com.vfaber.personalfinancemanager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BudgetDTO {
    private long id;
    private float amount;
    private Date startDate;
    private Date endDate;
}
