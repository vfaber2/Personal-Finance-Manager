package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
