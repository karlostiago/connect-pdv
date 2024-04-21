package com.util.commons.entity.cardMachine;

import com.util.commons.entity.cash.Cash;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import java.math.BigDecimal;

@Entity
@Data
public class CardMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal debitRate = BigDecimal.ZERO;

    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal creditRate= BigDecimal.ZERO;

    private int debitDays;

    private int creditDays;

    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal anticipationRate = BigDecimal.ZERO;

    @ManyToOne
    private Cash bank;
}
