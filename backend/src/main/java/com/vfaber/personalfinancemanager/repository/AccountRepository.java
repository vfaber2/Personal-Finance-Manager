package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.entity.AccountEntity;
import com.vfaber.personalfinancemanager.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends CrudRepository<AccountEntity, UUID> {

    Optional<AccountEntity> findByAccountHolder(UserEntity accountHolder);

    Optional<AccountEntity> findByIBAN(String IBAN);

    List<AccountEntity> findAll();
}
