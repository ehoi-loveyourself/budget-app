package com.bm.budget.service.response;

import lombok.Data;

import java.math.BigDecimal;

public class CalendarDay {
    private int day;
    private BigDecimal income;
    private BigDecimal expense;

    public CalendarDay(int day, BigDecimal income, BigDecimal expense) {
        this.day = day;
        this.income = income;
        this.expense = expense;
    }

    public BigDecimal net() {
        return income.subtract(expense);
    }
}
