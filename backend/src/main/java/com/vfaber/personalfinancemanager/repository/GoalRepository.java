package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.Goal;
import com.vfaber.personalfinancemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    Optional<List<Goal>> findGoalsByCurrentAmount(float currentAmount);

    Optional<List<Goal>> findGoalsByTargetAmount(float targetAmount);

    Optional<List<Goal>> findGoalsByUser(User user);

    Optional<List<Goal>> findGoalsByName(String name);

}
