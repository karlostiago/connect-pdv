package com.util.commons.entity.payable;

import com.util.commons.entity.payableType.PayableType;
import com.util.commons.entity.supplier.Supplier;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Payable  {

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
