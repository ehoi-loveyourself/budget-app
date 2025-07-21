package com.bm.budget.service;

import com.bm.budget.domain.Category;
import com.bm.budget.domain.Income;
import com.bm.budget.domain.User;
import com.bm.budget.repository.CategoryRepository;
import com.bm.budget.repository.IncomeRepository;
import com.bm.budget.repository.UserRepository;
import com.bm.budget.service.request.IncomeCreateRequest;
import com.bm.budget.service.response.IncomeResponse;
import com.bm.budget.util.exception.ErrorCode;
import com.bm.budget.util.exception.custom.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class IncomeService {

    private final CategoryRepository categoryRepository;
    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    public IncomeResponse createIncome(IncomeCreateRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.CATEGORY_NOT_FOUND));

        Long userId = 1L; // todo 추후 삭제
        User user = userRepository.findById(userId)
                .orElseThrow();

        Income income = Income.builder()
                .user(user)
                .category(category)
                .amount(request.getAmount())
                .memo(request.getMemo())
                .receivedAt(request.getTransactedAt())
                .build();

        Income save = incomeRepository.save(income);

        log.debug("[수입 기록] {}", save);

        return IncomeResponse.from(save);
    }
}
