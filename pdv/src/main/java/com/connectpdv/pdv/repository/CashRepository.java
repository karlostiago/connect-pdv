package com.connectpdv.pdv.repository;

import com.util.commons.entity.cash.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface CashRepository extends JpaRepository<Cash, Long> {
    @Query("select c from Cash c where c.types = 'CASH' and c.closingDate is null")
    Optional<Cash> fetchOpenCash();
}
