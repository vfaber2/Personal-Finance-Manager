package com.vfaber.personalFinanceManager.repository;


import com.vfaber.personalFinanceManager.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);
}
