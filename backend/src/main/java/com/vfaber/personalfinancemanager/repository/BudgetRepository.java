package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.BudgetEntity;
import com.vfaber.personalfinancemanager.model.CategoryEntity;
import com.vfaber.personalfinancemanager.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity, Long> {

    Optional<List<BudgetEntity>> findBudgetsByUserEntity(UserEntity userEntity);

    Optional<BudgetEntity> findBudgetByUserEntityAndId(UserEntity userEntity, Long id);

    Optional<List<BudgetEntity>> findBudgetsByStartDate(Date startDate);

    Optional<List<BudgetEntity>> findBudgetsByEndDate(Date endDate);

    Optional<List<BudgetEntity>> findBudgetsByCategoryEntity(CategoryEntity categoryEntity);
}
