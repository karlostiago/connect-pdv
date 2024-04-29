package com.util.commons.entity;

import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = false)
@ExcludedCoverage
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PayableInstallment extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NumberFormat(pattern = "##,##0.00")
    private Double totalValue;

    @NumberFormat(pattern = "##,##0.00")
    private Double discountValue;

    @NumberFormat(pattern = "##,##0.00")
    private Double increaseValue;

    @NumberFormat(pattern = "##,##0.00")
    private Double paidValue;


    @NumberFormat(pattern = "##,##0.00")
    private Double remainingValue;

    private int settled;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date creationDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dueDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    @ManyToOne
    private Payable payable;
}
