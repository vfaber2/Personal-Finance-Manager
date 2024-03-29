package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<List<UserEntity>> findUsersByFirstName(String firstName);

    Optional<List<UserEntity>> findUsersByLastName(String lastName);

    Optional<UserEntity> findUserByUsername(String username);

    Optional<UserEntity> findUserByEmail(String email);


}
