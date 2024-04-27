package com.connectpdv.pdv.serviceImpl;

import com.connectpdv.pdv.exceptions.notify.NotificationException;
import com.connectpdv.pdv.repository.CashRepository;
import com.connectpdv.pdv.security.ApplicationContext;
import com.connectpdv.pdv.service.CashService;
import com.util.commons.entity.cash.Cash;
import com.util.commons.entity.cashRegister.CashRegister;
import com.util.commons.entity.user.User;
import com.util.commons.enums.cash.CashType;
import com.util.commons.enums.entryStyle.EntryStyle;
import com.util.commons.enums.entryType.EntryType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;
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
            ApplicationContext context = ApplicationContext.getInstance();

            User user = userServiceImpl.findUserBy(context.getUserApplicationContext());
            String observation = applyDefaultDescriptionBasedOnCashTypeOf(cash);

            LocalDate actualDateOpenCash = LocalDate.now();

            cash.setRegisterDate(actualDateOpenCash);
            cash.setUser(user);

            sanitizeBankCashDetailsOf(cash);

            if (isOpeningValueGreaterThanDefault(cash)) {
                CashRegister cashBuilder = buildCashRegister(cash, user, observation);
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
    public boolean cashIsOpen() {
        return cashRepository.fetchOpenCash().isPresent();
    }

    private boolean isOpeningValueGreaterThanDefault(Cash cash) {
        BigDecimal openingValue = cash.getOpeningValue();
        boolean isGreaterThanDefault = openingValue.compareTo(BigDecimal.ZERO) > VALUE_DEFAULT_CASH_OPERATION;

        if (isGreaterThanDefault) {
            String description = switch (cash.getTypes()) {
                case CASH -> INCOMING_CASH_DESCRIPTION;
                case SAFE -> INCOMING_SAFE_DESCRIPTION;
                case BANK -> INCOMING_BANK_DESCRIPTION;
                default -> NO_AVAILABLE_REGISTER_FOUND;
            };
            cash.setDescription(description);
        }
        return isGreaterThanDefault;
    }

    private boolean isOpeningValueEqualToDefault(Cash cash) {
        return cash.getOpeningValue().compareTo(BigDecimal.ZERO) == VALUE_DEFAULT_CASH_OPERATION;
    }

    private void validateCashOpenOfDaysAndValueAcceptable(Cash cash) {
        if (cash == null) {
            throw new NotificationException(CASH_NOT_OPEN_OR_EXISTES);
        }

        if (CashType.CASH.equals(cash.getTypes()) && cashIsOpen()) {
            throw new NotificationException(DAYS_OLD_CASH_OPEN);
        }

        if (cash.getOpeningValue().compareTo(BigDecimal.ZERO) < VALUE_DEFAULT_CASH_OPERATION) {
            throw new NotificationException(VALUE_NOT_ACCEPTABLE);
        }
    }

    private String applyDefaultDescriptionBasedOnCashTypeOf(Cash cash) {
        if (cash == null) {
            throw new IllegalArgumentException(CASH_NOT_OPEN_OR_EXISTES);
        }
        String observation = cash.getDescription();

        if (StringUtils.isBlank(observation)) {
            String defaultDescription = switch (cash.getTypes()) {
                case CASH -> DAILY_CASH_DESCRIPTION;
                case SAFE -> SAFE_CASH_DESCRIPTION;
                case BANK -> BANK_CASH_DESCRIPTION;
                default -> CASH_NOT_RECOGNIZED;
            };
            cash.setDescription(defaultDescription);
        }
        return observation;
    }

    private void sanitizeBankCashDetailsOf(Cash cash) {
        if (cash == null) {
            throw new NotificationException(CASH_NOT_OPEN_OR_EXISTES);
        }

        if (cash.getTypes().equals(CashType.BANK)) {
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