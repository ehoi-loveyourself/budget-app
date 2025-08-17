package com.bm.budget.service;

import com.bm.budget.domain.Category;
import com.bm.budget.domain.Division;
import com.bm.budget.domain.Ledger;
import com.bm.budget.domain.User;
import com.bm.budget.repository.CategoryRepository;
import com.bm.budget.repository.DayAgg;
import com.bm.budget.repository.LedgerRepository;
import com.bm.budget.repository.UserRepository;
import com.bm.budget.service.request.TransactionCreateRequest;
import com.bm.budget.service.response.CalendarDay;
import com.bm.budget.service.response.CalendarResponse;
import com.bm.budget.service.response.DailyResponse;
import com.bm.budget.service.response.TransactionResponse;
import com.bm.budget.util.exception.ErrorCode;
import com.bm.budget.util.exception.custom.NotFoundException;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionService {

    private final CategoryRepository categoryRepository;
    private final LedgerRepository ledgerRepository;
    private final UserRepository userRepository;

    public TransactionResponse createTransaction(Long userId, TransactionCreateRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.CATEGORY_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow();

        Ledger ledger = Ledger.builder()
                .user(user)
                .category(category)
                .division(request.getDivision())
                .amount(request.getAmount())
                .memo(request.getMemo())
                .occurredAt(request.getOccurredAt())
                .build();

        Ledger save = ledgerRepository.save(ledger);

        log.debug("[거래 기록] {}", save);

        return TransactionResponse.from(save);
    }

    @Transactional(readOnly = true)
    public List<TransactionResponse> readTransactionsInfiniteScroll(Long userId, Long size, Long ledgerId, LocalDate occurredDate) {
        List<Ledger> ledgers = ledgerId == null || occurredDate == null
                ? ledgerRepository.readTransactionsInfiniteScroll(userId, size)
                : ledgerRepository.readTransactionsInfiniteScroll(userId, size, ledgerId, occurredDate);

        return ledgers.stream()
                .map(TransactionResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public DailyResponse readDailyTransactions(long userId, @Nullable LocalDate date) {
        LocalDate d = date == null ? LocalDate.now() : date;

        // 하루의 거래 내역
        List<Ledger> dailyTransactions = ledgerRepository.findByUserIdAndOccurredAt(userId, d);
        List<TransactionResponse> transactions = dailyTransactions.stream()
                .map(TransactionResponse::from)
                .toList();

        // 수입 합
        BigDecimal incomeSum = dailyTransactions.stream()
                .filter(t -> t.getDivision() == Division.INCOME)
                .map(Ledger::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 지출 합
        BigDecimal expenseSum = dailyTransactions.stream()
                .filter(t -> t.getDivision() == Division.EXPENSE)
                .map(Ledger::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal net = incomeSum.subtract(expenseSum);

        return DailyResponse.builder()
                .userId(userId)
                .incomeSum(incomeSum)
                .expenseSum(expenseSum)
                .net(net)
                .transactions(transactions)
                .build();
    }


    @Transactional(readOnly = true)
    public CalendarResponse readMonthlyTransactions(Long userId, int year, int month) {
        LocalDate first = LocalDate.of(year, month, 1);
        LocalDate next = first.plusMonths(1);

        // 1) DB에서 일자별 합계를 가져온다 (프로젝션 사용)
        List<DayAgg> rows = ledgerRepository.aggregateByDay(userId, first, next);

        // 2) 결과를 day -> (income, expense)로 변환
        int lastDay = YearMonth.of(year, month).lengthOfMonth();
        BigDecimal[] income = new BigDecimal[lastDay + 1];
        BigDecimal[] expense = new BigDecimal[lastDay + 1];
        Arrays.fill(income, BigDecimal.ZERO);
        Arrays.fill(expense, BigDecimal.ZERO);

        for (DayAgg row : rows) {
            int day = row.getLocalDay().getDayOfMonth();
            income[day] = row.getIncomeSum();
            expense[day] = row.getExpenseSum();
        }

        // 3) 1일부터 말일까지 response에 채우고, 총합도 계산하기
        List<CalendarDay> days = new ArrayList<>();
        BigDecimal incomeSum = BigDecimal.ZERO;
        BigDecimal expenseSum = BigDecimal.ZERO;

        for (int i = 1; i <= lastDay; i++) {
            BigDecimal inc = income[i];
            BigDecimal exp = expense[i];
            days.add(new CalendarDay(i, inc, exp));
            incomeSum.add(inc);
            expenseSum.add(exp);
        }

        BigDecimal net = incomeSum.subtract(expenseSum);

        return CalendarResponse.builder()
                .year(year)
                .month(month)
                .days(days)
                .incomeSum(incomeSum)
                .expenseSum(expenseSum)
                .net(net)
                .build();
    }
}
