package com.vfaber.personalfinancemanager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountEntity {
    @Id
    private UUID uuid;
    @Column(unique = true)
    private String IBAN;

    @ManyToOne
    @JoinColumn(name = "account_holder")
    private UserEntity accountHolder;

    private double balance;



}
