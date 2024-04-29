package com.util.commons.entity;

import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@EqualsAndHashCode(callSuper = false)
@ExcludedCoverage
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Payable extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String observation;

    @NumberFormat(pattern = "##,##0.00")
    private BigDecimal totalValue = BigDecimal.ZERO;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name="payable_type_id")
    private PayableType type;
}
