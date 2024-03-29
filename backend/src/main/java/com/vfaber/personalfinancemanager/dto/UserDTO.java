package com.vfaber.personalfinancemanager.dto;

import com.vfaber.personalfinancemanager.model.RoleEnum;
import com.vfaber.personalfinancemanager.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String profileInformation;
    private Set<RoleEnum> roles;

    public UserDTO(UserEntity userEntity) {
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.email = userEntity.getEmail();
        this.profileInformation = userEntity.getProfileInformation();
        this.roles = userEntity.getRoles();
    }
}
