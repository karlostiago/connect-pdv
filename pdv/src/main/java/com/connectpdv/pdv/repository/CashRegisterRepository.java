package com.connectpdv.pdv.repository;

import com.util.commons.entity.cash.Cash;
import com.util.commons.entity.cashRegister.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashRegisterRepository extends JpaRepository<CashRegister, Long> {
    List<CashRegister> findByCashEquals(Cash cash);
}
