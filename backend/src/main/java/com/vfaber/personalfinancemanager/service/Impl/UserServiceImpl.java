package com.vfaber.personalfinancemanager.service.Impl;

import com.vfaber.personalfinancemanager.dto.UserDTO;
import com.vfaber.personalfinancemanager.exceptions.UserNotFoundException;
import com.vfaber.personalfinancemanager.mapper.EntityMapper;
import com.vfaber.personalfinancemanager.model.UserEntity;
import com.vfaber.personalfinancemanager.repository.UserRepository;
import com.vfaber.personalfinancemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EntityMapper entityMapper) {
        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::new)
                .toList();
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public UserDTO getUserByUsername(String username) {
        return new UserDTO(userRepository.findUserByUsername(username).get());
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        userRepository.save(entityMapper.toUserEntity(userDTO));
    }

    @Override
    public void deleteUser(String username) throws UserNotFoundException {
        userRepository.delete(userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public void updateUser(String username, UserDTO userDTO) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        userEntity = entityMapper.updateUserEntity(userEntity, userDTO);
        userRepository.save(userEntity);
    }
}
