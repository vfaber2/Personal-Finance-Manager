package com.vfaber.personalFinanceManager.repository;

import com.vfaber.personalFinanceManager.entity.AccountEntity;
import com.vfaber.personalFinanceManager.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends CrudRepository<AccountEntity, UUID> {

    Optional<AccountEntity> findByAccountHolder(UserEntity accountHolder);

    Optional<AccountEntity> findByIBAN(String IBAN);
}
