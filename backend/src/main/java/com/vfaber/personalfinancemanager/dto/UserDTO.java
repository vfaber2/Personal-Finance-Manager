package com.vfaber.personalfinancemanager.dto;

import com.vfaber.personalfinancemanager.model.RoleEnum;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String profileInformation;
    private Set<RoleEnum> roles;
}
