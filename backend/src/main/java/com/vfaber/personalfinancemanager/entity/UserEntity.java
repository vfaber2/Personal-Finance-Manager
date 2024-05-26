package com.vfaber.personalFinanceManager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
}
