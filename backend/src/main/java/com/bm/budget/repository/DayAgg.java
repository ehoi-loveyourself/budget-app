package com.bm.budget.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

// 네이티브 쿼리 결과 매핑용 (프로젝션)
public interface DayAgg {
    LocalDate getLocalDay();      // DATE(occurred_at)
    BigDecimal getIncomeSum();    // SUM(CASE WHEN division='INCOME' THEN amount ELSE 0 END)
    BigDecimal getExpenseSum();   // SUM(CASE WHEN division='EXPENSE' THEN amount ELSE 0 END)
}
