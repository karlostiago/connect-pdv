package com.connectpdv.pdv.service;

import com.util.commons.entity.Cash;
import com.util.commons.filter.CashFilter;

import java.util.List;


public interface CashService {
   Cash openCash(Cash cash);

   Cash supply(Long cashId, Cash cash);

   List<Cash> getOpeningCashBy(CashFilter cashFilter);

   List<Cash> findAllOpeningCash();

   boolean cashIsOpen();

}
