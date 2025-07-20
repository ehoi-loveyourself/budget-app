package com.bm.budget.controller;

import com.bm.budget.service.IncomeService;
import com.bm.budget.service.request.IncomeCreateRequest;
import com.bm.budget.service.response.IncomeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/incomes")
@RestController
public class IncomeController {

    private final IncomeService incomeService;

    // 지출 등록
    @PostMapping
    public ResponseEntity<IncomeResponse> createIncome(@RequestBody IncomeCreateRequest request) {
        // todo 로그인 구현 이후, 로그인 여부 확인 처리 -> aop로 하면 되려나?

        IncomeResponse response = incomeService.createIncome(request);

        return ResponseEntity.ok(response);
    }
    
}
