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

    List<Ledger> findByUserIdAndOccurredAt(long userId, LocalDate d);

    @Query(value = """
        SELECT
          DATE(t.occurred_at) AS localDay,
          COALESCE(SUM(CASE WHEN t.division='INCOME'  THEN t.amount ELSE 0 END), 0) AS incomeSum,
          COALESCE(SUM(CASE WHEN t.division='EXPENSE' THEN t.amount ELSE 0 END), 0) AS expenseSum
        FROM ledgers t
        WHERE t.user_id = :userId
          AND t.deleted = 0
          AND t.occurred_at >= :start
          AND t.occurred_at <  :end
        GROUP BY localDay
        ORDER BY localDay
    """, nativeQuery = true)
    List<DayAgg> aggregateByDay(Long userId, LocalDate start, LocalDate end);
}
