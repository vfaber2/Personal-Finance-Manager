package com.vfaber.personalfinancemanager.service;

import com.vfaber.personalfinancemanager.dto.UserDTO;
import com.vfaber.personalfinancemanager.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserByUsername(String username);

    void saveUser(UserDTO user);

    void deleteUser(String username) throws UserNotFoundException;

    void updateUser(String username, UserDTO userDTO)throws UserNotFoundException;
}
