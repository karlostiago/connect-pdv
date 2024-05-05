package com.connectpdv.pdv.repository;

import com.util.commons.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CashRepository extends JpaRepository<Cash, Long> {
    @Query("select c from Cash c WHERE c.types = 'CAIXA' AND c.closingDate is null")
    Optional<Cash> fetchOpenCash();

    @Query("SELECT c FROM Cash c WHERE c.types = 'CAIXA' OR c.types = 'COFRE' AND CAST(c.registerDate AS date) = ?1 ORDER BY c.id DESC")
    List<Cash> findCashByRegisterDate(LocalDate date);

    @Query("SELECT c FROM Cash c WHERE c.closingDate is null")
    List<Cash> findOpenCash();
}
