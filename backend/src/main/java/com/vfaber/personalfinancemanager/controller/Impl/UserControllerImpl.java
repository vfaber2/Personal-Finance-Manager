package com.vfaber.personalFinanceManager.controller.Impl;

import com.vfaber.personalFinanceManager.controller.UserController;
import com.vfaber.personalFinanceManager.dto.UserDto;
import com.vfaber.personalFinanceManager.entity.UserEntity;
import com.vfaber.personalFinanceManager.exceptions.UsernameNotFoundException;
import com.vfaber.personalFinanceManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserRepository userRepository;

    @PostMapping("/addUser")
    @Override
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.save(convertUserEntityFromUserDto(userDto));
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/updateUser")
    @Override
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {

        if (userRepository.findByUsername(userDto.getUsername()).isEmpty()) return ResponseEntity.notFound().build();

        userRepository.save(convertUserEntityFromUserDto(userDto));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteUser/{username}")
    @Override
    public ResponseEntity<UserDto> deleteUser(@PathVariable String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            userRepository.delete(userRepository.findByUsername(username).get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/getUser/{username}")
    @Override
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        try {
            UserDto userDto = convertUserEntityToUserDto(
                    userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username))
            );
            return ResponseEntity.ok(userDto);
        } catch (UsernameNotFoundException _) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllUsers")
    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = new ArrayList<>();

        userRepository.findAll().stream().map(this::convertUserEntityToUserDto).forEach(userDtos::add);

        return ResponseEntity.ok(userDtos);
    }

    private UserDto convertUserEntityToUserDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        userDto.setPassword(userEntity.getPassword());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        return userDto;
    }

    private UserEntity convertUserEntityFromUserDto(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        return userEntity;
    }
}
