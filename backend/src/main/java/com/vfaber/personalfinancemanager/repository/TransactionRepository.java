package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.AccountEntity;
import com.vfaber.personalfinancemanager.model.CategoryEntity;
import com.vfaber.personalfinancemanager.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    Optional<List<TransactionEntity>> findTransactionsByAmount(float amount);

    Optional<List<TransactionEntity>> findTransactionsByAccountEntity(AccountEntity accountEntity);

    Optional<List<TransactionEntity>> findTransactionsByDate(Date date);

    Optional<List<TransactionEntity>> findTransactionsByCategoryEntity(CategoryEntity categoryEntity);

}
