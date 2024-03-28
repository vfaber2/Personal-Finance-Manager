package com.vfaber.personalfinancemanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date date;
    private float amount;
    private String type;
    @ManyToOne
    //@JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    //@JoinColumn(name = "category_id")
    private Category category;

}
