package com.vfaber.personalfinancemanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    //@JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String accountType;
    private float balance;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

}
