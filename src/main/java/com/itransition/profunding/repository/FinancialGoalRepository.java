package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.FinancialGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, Long>{

    void deleteAllByRootProject_Id(Long id);
}
