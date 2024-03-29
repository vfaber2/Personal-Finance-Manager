package com.vfaber.personalfinancemanager.controller;

import com.vfaber.personalfinancemanager.dto.UserDTO;
import com.vfaber.personalfinancemanager.exceptions.UserNotFoundException;
import com.vfaber.personalfinancemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok().body(
                userService.getAllUsers()
        );
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam String username) {
        try {
            userService.deleteUser(username);
            return ResponseEntity.ok().body(generateDeleteSuccessMessage(username));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateDeleteFailureMessage(username));
        }
    }

    private String generateDeleteSuccessMessage(String username) {
        return String.format("Successfully deleted %s", username);
    }

    private String generateDeleteFailureMessage(String username) {
        return String.format("Failure with deletion of %s", username);
    }

    @PutMapping("/editUser")
    public ResponseEntity<String> editUser(@RequestParam String username, @RequestBody UserDTO userDTO) {
        try {
            userService.updateUser(username, userDTO);
            return ResponseEntity.ok().body(generateUpdateSuccessMessage(username));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateUpdateFailureMessage(username));
        }
    }

    private String generateUpdateFailureMessage(String username) {
        return String.format("Successfully edited %s", username);
    }

    private String generateUpdateSuccessMessage(String username) {
        return String.format("Failure with edit of %s", username);
    }


}
