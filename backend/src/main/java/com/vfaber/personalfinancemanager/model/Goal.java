package com.vfaber.personalfinancemanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private float targetAmount;
    private float currentAmount;
    private Date targetDate;
    @ManyToOne
    //@JoinColumn(name = "user_id")
    private User user;


}
