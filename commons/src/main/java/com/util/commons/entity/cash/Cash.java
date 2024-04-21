package com.util.commons.entity.cash;

import com.util.commons.entity.user.User;
import com.util.commons.enums.cash.CashType;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Cash {
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
    private CashType type;

    private String agency;

    private String account;

    @ManyToOne
    private User user;
}
