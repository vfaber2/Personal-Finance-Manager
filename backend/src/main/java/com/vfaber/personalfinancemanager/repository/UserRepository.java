package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<List<User>> findUsersByFirstName(String firstName);

    Optional<List<User>> findUsersByLastName(String lastName);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

}
