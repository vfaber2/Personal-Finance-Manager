package com.vfaber.personalfinancemanager.mapper;

import com.vfaber.personalfinancemanager.dto.UserDTO;
import com.vfaber.personalfinancemanager.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public UserEntity toUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setProfileInformation(userDTO.getProfileInformation());
        userEntity.setRoles(userDTO.getRoles());
        return userEntity;
    }

    public UserEntity updateUserEntity(UserEntity userEntity, UserDTO userDTO) {
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setProfileInformation(userDTO.getProfileInformation());
        userEntity.setRoles(userDTO.getRoles());

        return userEntity;
    }
}
