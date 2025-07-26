package com.bm.budget.service;

import com.bm.budget.domain.Category;
import com.bm.budget.domain.Ledger;
import com.bm.budget.domain.User;
import com.bm.budget.repository.CategoryRepository;
import com.bm.budget.repository.LedgerRepository;
import com.bm.budget.repository.UserRepository;
import com.bm.budget.service.request.TransactionCreateRequest;
import com.bm.budget.service.response.TransactionResponse;
import com.bm.budget.util.exception.ErrorCode;
import com.bm.budget.util.exception.custom.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionService {

    private final CategoryRepository categoryRepository;
    private final LedgerRepository ledgerRepository;
    private final UserRepository userRepository;

    public TransactionResponse createTransaction(TransactionCreateRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.CATEGORY_NOT_FOUND));

        Long userId = 1L; // todo 추후 삭제
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
    public List<TransactionResponse> readTransactionsInfiniteScroll(Long size, Long ledgerId, LocalDate occurredDate) {
        Long userId = 1L; // todo 추후 삭제

        List<Ledger> ledgers = ledgerId == null || occurredDate == null
                ? ledgerRepository.readTransactionsInfiniteScroll(userId, size)
                : ledgerRepository.readTransactionsInfiniteScroll(userId, size, ledgerId, occurredDate);

        return ledgers.stream()
                .map(TransactionResponse::from)
                .toList();
    }
}
