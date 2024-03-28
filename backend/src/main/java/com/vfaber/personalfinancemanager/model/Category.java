package com.vfaber.personalfinancemanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Transaction> transactions;


}
