package com.vfaber.personalFinanceManager.controller;

import com.vfaber.personalFinanceManager.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {
    ResponseEntity<UserDto> addUser(UserDto userDto);

    ResponseEntity<UserDto> updateUser(UserDto userDto);

    ResponseEntity<UserDto> deleteUser(String username);

    ResponseEntity<UserDto> getUser(String username);

    ResponseEntity<List<UserDto>> getAllUsers();
}
