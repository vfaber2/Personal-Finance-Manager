package com.vfaber.personalfinancemanager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GoalDTO {
    private long id;
    private String name;
    private float targetAmount;
    private float currentAmount;
    private Date targetDate;
}
