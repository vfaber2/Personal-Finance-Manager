package com.vfaber.personalfinancemanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private float amount;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    //@JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    //@JoinColumn(name = "category_id")
    private Category category;

}
