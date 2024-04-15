package com.util.commons.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
public class CashierDTO {
    private ZonedDateTime cashierDateOpening;
    private ZonedDateTime cashierDateClosing;
    private BigDecimal cashBalanceInitial;
    private BigDecimal cashFinalBalance;
}
