package com.util.commons.entity.installment;

import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = false)
@ExcludedCoverage
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Installment extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NumberFormat(pattern = "##,##0.00")
    private BigDecimal totalValue = BigDecimal.ZERO;

    @NumberFormat(pattern = "##,##0.00")
    private BigDecimal increaseValue = BigDecimal.ZERO;

    @NumberFormat(pattern = "##,##0.00")
    private BigDecimal discountValue= BigDecimal.ZERO;

    @NumberFormat(pattern = "##,##0.00")
    private BigDecimal receivedValue= BigDecimal.ZERO;

    @NumberFormat(pattern = "##,##0.00")
    private BigDecimal remainingValue= BigDecimal.ZERO;

    private int settled;

    private int sequence;

   /* @ManyToOne
    private Receive receive;*/

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date creationDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

}
