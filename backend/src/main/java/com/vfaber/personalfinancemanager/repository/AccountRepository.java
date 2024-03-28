package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.Account;
import com.vfaber.personalfinancemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<List<Account>> findAccountsByBalance(float balance);

    Optional<List<Account>> findAccountsByUser(User user);

    Optional<Account> findAccountByUserAndBalance(User user, float balance);
}
