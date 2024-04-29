package com.util.commons.entity;

import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.NumberFormat;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@EqualsAndHashCode(callSuper = false)
@ExcludedCoverage
@Data
public class CardMachine extends AbstractEntity {

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
