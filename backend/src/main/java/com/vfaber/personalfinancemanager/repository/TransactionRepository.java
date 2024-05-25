package com.vfaber.personalFinanceManager.repository;

import com.vfaber.personalFinanceManager.entity.AccountEntity;
import com.vfaber.personalFinanceManager.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends CrudRepository<TransactionEntity, UUID> {

    Optional<TransactionEntity> findById(UUID id);

    Optional<List<TransactionEntity>> findAllByAccountFrom(AccountEntity accountFrom);

    Optional<List<TransactionEntity>> findAllByAccountTo(AccountEntity accountTo);

}
