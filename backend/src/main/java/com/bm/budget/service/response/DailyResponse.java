package com.bm.budget.service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@ToString
@Builder
@Getter
public class DailyResponse {
    private Long userId;
    private BigDecimal expenseSum;
    private BigDecimal incomeSum;
    private BigDecimal net;
    private List<TransactionResponse> transactions;
}
