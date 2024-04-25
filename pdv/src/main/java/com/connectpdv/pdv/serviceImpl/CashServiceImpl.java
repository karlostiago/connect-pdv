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
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CashServiceImpl implements CashService {

    private final static String  DAYS_OLD_CASH_OPEN = "Existe Caixa de dias anteriores em aberto, verificar.";
    private final static String  VALUE_NOT_ACCEPTABLE = "Valor informado é inválido.";
    private final static String  MESSAGE_ERROR = "Erro no processo de abertura: ";
    private final static String  INCOMING_CASH_DESCRIPTION = "Abertura de caixa";
    private final static String  INCOMING_SAFE_DESCRIPTION = "Abertura de Cofre";
    private final static String  INCOMING_BANK_DESCRIPTION = "Abertura de banco";
    private final static String  DAILY_CASH_DESCRIPTION = "Caixa diário";
    private final static String  SAFE_CASH_DESCRIPTION = "Cofre";
    private final static String  BANK_CASH_DESCRIPTION = "Banco";
    private final static Integer VALUE_DEFAULT_CASH_OPERATION = 0;

    private final CashRepository cashRepository;
    private final UserServiceImpl userServiceImpl;
    private final CashRegisterServiceImpl cashRegisterServiceImpl;

    private String descriptionTypeCash;

    public CashServiceImpl(CashRepository cashRepository, UserServiceImpl userServiceImpl,
                           CashRegisterServiceImpl cashRegisterServiceImpl) {

        this.cashRepository = cashRepository;
        this.userServiceImpl = userServiceImpl;
        this.cashRegisterServiceImpl = cashRegisterServiceImpl;
    }

    @Override
    public Cash openCash(Cash cash) {
        try {
            if (CashType.CASH.equals(cash.getTypes()) && cashIsOpen())
                throw new NotificationException(DAYS_OLD_CASH_OPEN);

            if (cash.getOpeningValue().compareTo(BigDecimal.ZERO) < VALUE_DEFAULT_CASH_OPERATION)
                throw new NotificationException(VALUE_NOT_ACCEPTABLE);

            ApplicationContext context = ApplicationContext.getInstance();
            User user = userServiceImpl.findUserBy(context.getUserApplicationContext());

            if (CashType.CASH.equals(cash.getTypes()))
                descriptionTypeCash = cash.getDescription().isBlank() ? DAILY_CASH_DESCRIPTION : cash.getDescription();

            else if (CashType.SAFE.equals(cash.getTypes()))
                descriptionTypeCash = cash.getDescription().isBlank() ? SAFE_CASH_DESCRIPTION : cash.getDescription();

            else if (CashType.BANK.equals(cash.getTypes()))
                descriptionTypeCash = cash.getDescription().isBlank() ? BANK_CASH_DESCRIPTION : cash.getDescription();

            LocalDate actualDateOpenCash = LocalDate.now();

            cash.setDescription(descriptionTypeCash);
            cash.setRegisterDate(actualDateOpenCash);
            cash.setUser(user);

            if (cash.getTypes().equals(CashType.BANK)) {
                cash.setAgency(cash.getAgency().replaceAll("\\D", ""));
                cash.setAccount(cash.getAccount().replaceAll("\\D", ""));
            }

            if (cash.getOpeningValue().compareTo(BigDecimal.ZERO) > VALUE_DEFAULT_CASH_OPERATION) {
                String observation = CashType.CASH.equals(cash.getTypes()) ? INCOMING_CASH_DESCRIPTION
                        : CashType.SAFE.equals(cash.getTypes()) ? INCOMING_SAFE_DESCRIPTION : INCOMING_BANK_DESCRIPTION;

                CashRegister cashBuilder = CashRegister.builder()
                        .observation(observation)
                        .totalValue(cash.getOpeningValue())
                        .entryType(EntryType.INITIAL_BALANCE)
                        .entryStyle(EntryStyle.INCOME)
                        .cash(cash)
                        .user(user)
                        .registerDate(LocalDate.now())
                        .build();
                cashRegisterServiceImpl.register(cashBuilder);

            } else if (cash.getOpeningValue().compareTo(BigDecimal.ZERO) == VALUE_DEFAULT_CASH_OPERATION) {
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
}
