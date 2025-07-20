package com.bm.budget.repository;

import com.bm.budget.domain.Expense;
import com.bm.budget.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
