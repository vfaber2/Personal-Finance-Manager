package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.AccountEntity;
import com.vfaber.personalfinancemanager.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<List<AccountEntity>> findAccountsByBalance(float balance);

    Optional<List<AccountEntity>> findAccountsByUserEntity(UserEntity userEntity);

    Optional<AccountEntity> findAccountByUserEntityAndBalance(UserEntity userEntity, float balance);
}
