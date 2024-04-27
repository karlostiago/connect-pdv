package com.connectpdv.pdv.serviceImpl;

import com.connectpdv.pdv.exceptions.notify.NotificationException;
import com.connectpdv.pdv.repository.CashRegisterRepository;
import com.connectpdv.pdv.service.CashRegisterService;
import com.util.commons.entity.cash.Cash;
import com.util.commons.entity.cashRegister.CashRegister;
import com.util.commons.enums.entryStyle.EntryStyle;
import com.util.commons.enums.entryType.EntryType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import static com.util.commons.constants.CashConstants.*;

@Component
public class CashRegisterServiceImpl implements CashRegisterService {

    private final CashRegisterRepository cashRegisterRepository;

    public CashRegisterServiceImpl(CashRegisterRepository cashRegisterRepository) {
        this.cashRegisterRepository = cashRegisterRepository;
    }

    @Override
    public void register(CashRegister cashRegister) {
        try {
            cashRegister.setRegisterDate(Date.valueOf(LocalDate.now()).toLocalDate());

            if (cashRegister.getCash() == null) {
                throw new NotificationException(NO_AVAILABLE_REGISTER_FOUND);
            }

            if (EntryStyle.OUTGOING.equals(cashRegister.getEntryStyle())) {
                BigDecimal totalCash = cashRegister.getCash().getTotalValue();

                if (cashRegister.getTotalValue().compareTo(totalCash) > VALUE_DEFAULT_CASH_OPERATION) {
                    throw new NotificationException(INSUFFICIENT_BALANCE);
                }

                if (cashRegister.getTotalValue().compareTo(BigDecimal.ZERO) > VALUE_DEFAULT_CASH_OPERATION) {
                    cashRegister.setTotalValue(cashRegister.getTotalValue().multiply(BigDecimal.valueOf(-1)));
                }
            }

            if (StringUtils.isBlank(cashRegister.getObservation())) {
                String observation = "";
                if (EntryType.BLEED.equals(cashRegister.getEntryType())) {
                    observation = BLEED_OBSERVATION;
                } else if (EntryType.SUPPLY.equals(cashRegister.getEntryType())) {
                    observation = SUPPLY_OBSERVATION;
                }
                cashRegister.setObservation(observation);
            }

            cashRegisterRepository.save(cashRegister);
        } catch (NotificationException e) {
            throw new NotificationException(CASH_REGISTER_ERROR);
        }
    }

    @Override
    public List<CashRegister> getRegistersFor(Cash cash) {
        return cashRegisterRepository.findByCashEquals(cash);
    }
}
