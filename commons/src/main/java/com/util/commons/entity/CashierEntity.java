package com.util.commons.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "cashier")
@Getter
@Setter
public class CashierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ZonedDateTime cashierDateOpening;
    private ZonedDateTime cashierDateClosing;
    private BigDecimal cashBalanceInitial;
    private BigDecimal cashFinalBalance;
}
