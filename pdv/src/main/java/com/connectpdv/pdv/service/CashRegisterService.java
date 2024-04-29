package com.connectpdv.pdv.service;

import com.util.commons.entity.Cash;
import com.util.commons.entity.CashRegister;

import java.util.List;

public interface CashRegisterService {
    void register(CashRegister cashRegister);
    List<CashRegister> getRegistersFor(Cash cash);
}
