package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.entity.AccountEntity;
import com.vfaber.personalfinancemanager.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends CrudRepository<TransactionEntity, UUID> {

    Optional<TransactionEntity> findById(UUID id);

    Optional<List<TransactionEntity>> findAllByAccountFrom(AccountEntity accountFrom);

    Optional<List<TransactionEntity>> findAllByAccountTo(AccountEntity accountTo);

    List<TransactionEntity> findAll();

}
