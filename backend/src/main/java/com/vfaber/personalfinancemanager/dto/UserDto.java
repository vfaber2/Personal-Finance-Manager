package com.vfaber.personalFinanceManager.dto;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
