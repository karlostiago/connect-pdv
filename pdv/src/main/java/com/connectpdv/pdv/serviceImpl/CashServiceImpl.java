package com.connectpdv.pdv.serviceImpl;

import com.connectpdv.pdv.exceptions.notify.NotificationException;
import com.connectpdv.pdv.repository.CashRepository;
import com.connectpdv.pdv.service.CashService;
import com.util.commons.entity.Cash;
import com.util.commons.entity.CashRegister;
import com.util.commons.entity.User;
import com.util.commons.enums.cash.CashType;
import com.util.commons.enums.entryStyle.EntryStyle;
import com.util.commons.enums.entryType.EntryType;
import com.util.commons.filter.CashFilter;
import com.util.commons.helper.DateHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.util.commons.constants.CashConstants.*;

@Component
public class CashServiceImpl implements CashService {

    private final CashRepository cashRepository;
    private final UserServiceImpl userServiceImpl;
    private final CashRegisterServiceImpl cashRegisterServiceImpl;

    public CashServiceImpl(CashRepository cashRepository, UserServiceImpl userServiceImpl,
                           CashRegisterServiceImpl cashRegisterServiceImpl) {

        this.cashRepository = cashRepository;
        this.userServiceImpl = userServiceImpl;
        this.cashRegisterServiceImpl = cashRegisterServiceImpl;
    }

    @Override
    public Cash openCash(Cash cash) {
        try {

            validateCashOpenOfDaysAndValueAcceptable(cash);

            User user = userServiceImpl.findUserBy(cash.getUser().getUserName());

            if (StringUtils.isBlank(cash.getDescription())) {
                applyDefaultDescriptionBasedOnCashTypeOf(cash);
            }

            LocalDate actualDateOpenCash = LocalDate.now();
            cash.setRegisterDate(actualDateOpenCash);
            cash.setUser(user);

            sanitizeBankCashDetailsOf(cash);

            if (cash.getOpeningValue().compareTo(BigDecimal.ZERO) > VALUE_DEFAULT_CASH_OPERATION) {
                CashRegister cashBuilder = buildCashRegister(cash, user, cash.getDescription());
                cashRegisterServiceImpl.register(cashBuilder);

            } else if (isOpeningValueEqualToDefault(cash)) {
                cash.setTotalValue(BigDecimal.valueOf(VALUE_DEFAULT_CASH_OPERATION));
            }

            cash.setTotalValue(cash.getOpeningValue());
            cashRepository.save(cash);

        } catch (NotificationException e) {
            throw new NotificationException(MESSAGE_ERROR + e.getMessage());
        }
        return cash;
    }

    @Override
    public Cash supply(Long cashId, Cash cash) {
        return null;
    }

    @Override
    public List<Cash> getOpeningCashBy(CashFilter cashFilter) {
        String registerDateFilter = cashFilter.getRegisterDate();

        if (StringUtils.isNotBlank(registerDateFilter)) {
            LocalDate registerDate = DateHelper.parseDateFrom(registerDateFilter);
            return cashRepository.findCashByRegisterDate(registerDate);
        }

        return findAllOpeningCash();
    }

    @Override
    public List<Cash> findAllOpeningCash() {
        return cashRepository.findOpenCash();
    }

    @Override
    public boolean cashIsOpen() {
        return cashRepository.fetchOpenCash().isPresent();
    }

    private boolean isOpeningValueEqualToDefault(Cash cash) {
        return cash.getOpeningValue().compareTo(BigDecimal.ZERO) == VALUE_DEFAULT_CASH_OPERATION;
    }

    private void validateCashOpenOfDaysAndValueAcceptable(Cash cash) {
        if (cash == null) {
            throw new NotificationException(CASH_NOT_OPEN_OR_EXISTES);
        }

        if (CashType.CAIXA.equals(cash.getTypes()) && cashIsOpen()) {
            throw new NotificationException(DAYS_OLD_CASH_OPEN);
        }

        if (cash.getOpeningValue().compareTo(BigDecimal.ZERO) < VALUE_DEFAULT_CASH_OPERATION) {
            throw new NotificationException(VALUE_NOT_ACCEPTABLE);
        }
    }

    private void applyDefaultDescriptionBasedOnCashTypeOf(Cash cash) {
        if (cash == null) {
            throw new IllegalArgumentException(CASH_NOT_OPEN_OR_EXISTES);
        }
        String observation = cash.getDescription();

        if (StringUtils.isBlank(observation)) {
            String defaultDescription = switch (cash.getTypes()) {
                case CAIXA -> DAILY_CASH_DESCRIPTION;
                case COFRE -> SAFE_CASH_DESCRIPTION;
                case BANCO -> BANK_CASH_DESCRIPTION;
                default -> CASH_NOT_RECOGNIZED;
            };
            cash.setDescription(defaultDescription);
        }
    }

    private void sanitizeBankCashDetailsOf(Cash cash) {
        if (cash == null) {
            throw new NotificationException(CASH_NOT_OPEN_OR_EXISTES);
        }

        if (cash.getTypes().equals(CashType.BANCO) 
            && cash.getAgency() != null || cash.getAccount() != null ) {

            assert cash.getAgency() != null;
            cash.setAgency(cash.getAgency().replaceAll("\\D", ""));
            cash.setAccount(cash.getAccount().replaceAll("\\D", ""));
        }
    }

    private CashRegister buildCashRegister(Cash cash, User user, String observation) {
        return CashRegister.builder()
                .observation(observation)
                .totalValue(cash.getOpeningValue())
                .entryType(EntryType.INITIAL_BALANCE)
                .entryStyle(EntryStyle.INCOME)
                .cash(cash)
                .user(user)
                .registerDate(LocalDate.now())
                .build();
    }
}