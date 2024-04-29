package com.connectpdv.pdv.service;

import com.util.commons.entity.Cash;


public interface CashService {
   Cash openCash(Cash cash);

   Cash supply(Long cashId, Cash cash);

   boolean cashIsOpen();

}
