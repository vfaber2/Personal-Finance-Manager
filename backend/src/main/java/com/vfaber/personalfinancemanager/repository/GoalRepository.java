package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.GoalEntity;
import com.vfaber.personalfinancemanager.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Long> {

    Optional<List<GoalEntity>> findGoalsByCurrentAmount(float currentAmount);

    Optional<List<GoalEntity>> findGoalsByTargetAmount(float targetAmount);

    Optional<List<GoalEntity>> findGoalsByUserEntity(UserEntity userEntity);

    Optional<List<GoalEntity>> findGoalsByName(String name);

}
