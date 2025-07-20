package com.bm.budget.controller;

import com.bm.budget.service.request.ExpenseCreateRequest;
import com.bm.budget.service.ExpenseService;
import com.bm.budget.service.response.ExpenseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/expenses")
@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    // 지출 등록
    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseCreateRequest request) {
        // todo 로그인 구현 이후, 로그인 여부 확인 처리 -> aop로 하면 되려나?

        ExpenseResponse response = expenseService.createExpense(request);

        return ResponseEntity.ok(response);
    }
}
