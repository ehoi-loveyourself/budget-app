package com.bm.budget.controller;

import com.bm.budget.service.TransactionService;
import com.bm.budget.service.request.TransactionCreateRequest;
import com.bm.budget.service.response.CalendarResponse;
import com.bm.budget.service.response.DailyResponse;
import com.bm.budget.service.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
        Long userId = 1L;
        TransactionResponse response = transactionService.createTransaction(userId, request);

        return ResponseEntity.ok(response);
    }

    // 마지막으로 읽은 가계부 id를 커서로 지정 + 한번에 읽어들일 size 지정
    @GetMapping("/infinite-scroll")
    public ResponseEntity<List<TransactionResponse>> readTransactionsInfiniteScroll(@RequestParam(defaultValue = "10") Long size,
                                                                      @RequestParam(required = false) Long ledgerId,
                                                                      @RequestParam(required = false) LocalDate occurredDate) {
        // todo 로그인 구현 이후, 로그인 여부 확인 처리
        Long userId = 1L;
        List<TransactionResponse> response = transactionService.readTransactionsInfiniteScroll(userId, size, ledgerId, occurredDate);

        return ResponseEntity.ok(response);
    }

    // 오늘 하루동안의 수입, 지출 내역 및 합계 내역 조회
    // date가 없이 들어오면 자동으로 오늘 날짜 세팅, 파라미터 들어오면 그 날짜 조회
    @GetMapping("/daily")
    public ResponseEntity<DailyResponse> readDailyTransactions(@RequestParam(required = false) LocalDate date) {
        // todo 로그인 구현 이후, 로그인 여부 확인 처리
        Long userId = 1L;
        DailyResponse response = transactionService.readDailyTransactions(userId, date);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/calender")
    public ResponseEntity<CalendarResponse> readMonthlyTransactions(@RequestParam int year,
                                                                    @RequestParam int month) {
        // todo 로그인 구현 이후, 로그인 여부 확인 처리
        Long userId = 1L;
        CalendarResponse response = transactionService.readMonthlyTransactions(userId, year, month);

        return ResponseEntity.ok(response);
    }

}
