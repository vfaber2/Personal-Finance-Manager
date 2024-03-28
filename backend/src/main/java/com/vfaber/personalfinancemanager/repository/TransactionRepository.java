package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.Account;
import com.vfaber.personalfinancemanager.model.Category;
import com.vfaber.personalfinancemanager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<List<Transaction>> findTransactionsByAmount(float amount);

    Optional<List<Transaction>> findTransactionsByAccount(Account account);

    Optional<List<Transaction>> findTransactionsByDate(Date date);

    Optional<List<Transaction>> findTransactionsByCategory(Category category);

}
