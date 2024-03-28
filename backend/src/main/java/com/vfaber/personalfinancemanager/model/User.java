package com.vfaber.personalfinancemanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String profileInformation;

    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles;
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
    @OneToMany(mappedBy = "user")
    private List<Budget> budgets;
    @OneToMany(mappedBy = "user")
    private List<Goal> goals;

}
