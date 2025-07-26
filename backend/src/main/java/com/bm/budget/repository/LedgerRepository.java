package com.bm.budget.repository;

import com.bm.budget.domain.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {

    @Query(
            value = """
                    select *
                      from ledgers
                     where user_id = :userId
                     order by occurred_at desc, id desc
                     limit :size
                    """,
            nativeQuery = true
    )
    List<Ledger> readTransactionsInfiniteScroll(Long userId, Long size);

    @Query(
            value = """
                    select *
                      from ledgers
                     where user_id = :userId
                       and (
                    	occurred_at < :cursorDate
                        or (occurred_at = :cursorDate and id < :cursorId)
                       )
                     order by occurred_at desc, id desc
                     limit :size
                    """,
            nativeQuery = true
    )
    List<Ledger> readTransactionsInfiniteScroll(Long userId, Long size, Long cursorId, LocalDate cursorDate);
}
