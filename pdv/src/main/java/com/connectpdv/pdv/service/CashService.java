package com.connectpdv.pdv.service;

import com.util.commons.entity.cash.Cash;
import com.util.commons.enums.cash.CashType;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface CashService {
   Cash openCash(Cash cash);

   Cash closeCash(Cash cash, String passwd);

   boolean cashIsOpen();

   List<Cash> listAll();

   List<Cash> filterCash(Cash cash, Pageable pageable);

   Optional<Cash> isOpenCash();

   List<Cash> availableCash();

   Optional<Cash> findCash(Long id);

   Optional<Cash> findCashOfUser(String userName);

   List<Cash> listAvailableBanksOfCash();

   List<Cash> listCashAvailableForType(CashType cashType);

   List<Cash> listOpenBanksTypeFilterBank(CashType cashType, Pageable pageable);
}
