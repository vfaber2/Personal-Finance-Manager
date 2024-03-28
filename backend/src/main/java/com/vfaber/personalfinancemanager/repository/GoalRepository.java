package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
}
