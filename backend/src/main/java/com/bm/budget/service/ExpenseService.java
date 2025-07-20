package com.bm.budget.service;

import com.bm.budget.domain.Category;
import com.bm.budget.domain.Expense;
import com.bm.budget.domain.User;
import com.bm.budget.repository.CategoryRepository;
import com.bm.budget.repository.ExpenseRepository;
import com.bm.budget.repository.UserRepository;
import com.bm.budget.service.request.ExpenseCreateRequest;
import com.bm.budget.service.response.ExpenseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExpenseService {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseResponse createExpense(ExpenseCreateRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow();
        // todo: 예외처리 구조 잡기

        Long userId = 1L; // todo 추후 삭제
        User user = userRepository.findById(userId)
                .orElseThrow();

        Expense expense = Expense.builder()
                .user(user)
                .category(category)
                .amount(request.getAmount())
                .memo(request.getMemo())
                .spentAt(request.getTransactedAt())
                .build();

        Expense save = expenseRepository.save(expense);

        log.debug("[지출 기록] {}", save);

        return ExpenseResponse.from(save);
    }
}
