package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.Budget;
import com.vfaber.personalfinancemanager.model.Category;
import com.vfaber.personalfinancemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Optional<List<Budget>> findBudgetsByUser(User user);

    Optional<Budget> findBudgetByUserAndId(User user, Long id);

    Optional<List<Budget>> findBudgetsByStartDate(Date startDate);

    Optional<List<Budget>> findBudgetsByEndDate(Date endDate);

    Optional<List<Budget>> findBudgetsByCategory(Category category);
}
