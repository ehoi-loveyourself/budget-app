package com.bm.budget.controller;

import com.bm.budget.service.TransactionService;
import com.bm.budget.service.request.TransactionCreateRequest;
import com.bm.budget.service.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    // 수입 or 지출 등록
    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionCreateRequest request) {
        // todo 로그인 구현 이후, 로그인 여부 확인 처리 -> aop로 하면 되려나?

        TransactionResponse response = transactionService.createTransaction(request);

        return ResponseEntity.ok(response);
    }

    // 마지막으로 읽은 가계부 id를 커서로 지정 + 한번에 읽어들일 size 지정
    @GetMapping("/infinite-scroll")
    public ResponseEntity<List<TransactionResponse>> readTransactionsInfiniteScroll(@RequestParam(defaultValue = "10") Long size,
                                                                      @RequestParam(required = false) Long ledgerId,
                                                                      @RequestParam(required = false) LocalDate occurredDate) {
        List<TransactionResponse> response = transactionService.readTransactionsInfiniteScroll(size, ledgerId, occurredDate);

        return ResponseEntity.ok(response);
    }
}
