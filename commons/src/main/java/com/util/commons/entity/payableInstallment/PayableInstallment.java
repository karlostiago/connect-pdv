package com.util.commons.entity.payableInstallment;

import com.util.commons.entity.payable.Payable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class PayableInstallment  {

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
