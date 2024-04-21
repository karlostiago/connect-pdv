package com.connectpdv.pdv.service;

import com.util.commons.entity.cash.Cash;
import com.util.commons.entity.cashRegister.CashRegister;

import java.util.List;

public interface CashRegisterService {
    CashRegister register(CashRegister cashRegister);
    List<CashRegister> getRegistersFor(Cash cash);
}
