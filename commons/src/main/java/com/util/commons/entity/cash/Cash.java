package com.util.commons.entity.cash;

import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import com.util.commons.entity.cashRegister.CashRegister;
import com.util.commons.entity.user.User;
import com.util.commons.enums.cash.CashType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = false)
@ExcludedCoverage
@Data
@DynamicInsert
@DynamicUpdate
public class Cash extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @NumberFormat(pattern = "##,##0.00")
    private BigDecimal openingValue = BigDecimal.ZERO;

    @NumberFormat(pattern = "##,##0.00")
    private BigDecimal totalValue = BigDecimal.ZERO;

    @NumberFormat(pattern = "##,##0.00")
    private BigDecimal closingValue = BigDecimal.ZERO;

    @NumberFormat(pattern = "##,##0.00")
    private BigDecimal entryValue = BigDecimal.ZERO;

    @NumberFormat(pattern = "##,##0.00")
    private BigDecimal exitValue = BigDecimal.ZERO;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate registerDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date closingDate;

    @Enumerated(EnumType.STRING)
    private CashType types;

    private String agency;

    private String account;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Transient
    private List<CashRegister> cashRegisters;
}
