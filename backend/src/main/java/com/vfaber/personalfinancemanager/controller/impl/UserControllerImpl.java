package com.vfaber.personalfinancemanager.controller.impl;

import com.vfaber.personalfinancemanager.controller.UserController;
import com.vfaber.personalfinancemanager.dto.UserDto;
import com.vfaber.personalfinancemanager.exceptions.UsernameNotFoundException;
import com.vfaber.personalfinancemanager.repository.UserRepository;
import com.vfaber.personalfinancemanager.service.Mapper;
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
    private final Mapper mapper = new Mapper();

    @PostMapping("/addUser")
    @Override
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.save(mapper.dtoToEntity(userDto));
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/updateUser")
    @Override
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {

        if (userRepository.findByUsername(userDto.getUsername()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userRepository.save(mapper.dtoToEntity(userDto));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteUser/{username}")
    @Override
    public ResponseEntity<UserDto> deleteUser(@PathVariable String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            userRepository.delete(userRepository.findByUsername(username).get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getUser/{username}")
    @Override
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        try {
            UserDto userDto = mapper.entityToDto(
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

        userRepository.findAll().stream().map(mapper::entityToDto).forEach(userDtos::add);

        return ResponseEntity.ok(userDtos);
    }
}
