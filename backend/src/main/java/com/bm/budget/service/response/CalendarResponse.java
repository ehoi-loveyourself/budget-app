package com.bm.budget.service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@ToString
@Builder
@Getter
public class CalendarResponse {
    private int year;
    private int month;
    private BigDecimal incomeSum;
    private BigDecimal expenseSum;
    private BigDecimal net;
    private List<CalendarDay> days;
}
