package com.util.commons.entity;

import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import java.util.Date;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = false)
@ExcludedCoverage
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Receipt extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NumberFormat(pattern = "#,##0.00")
    private Double totalValue;

    @NumberFormat(pattern = "#,##0.00")
    private Double discountValue;

    @NumberFormat(pattern = "#,##0.00")
    private Double increaseValue;

    @NumberFormat(pattern = "#,##0.00")
    private Double receivedValue;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date creationDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date processingDate;

    @ManyToOne
    private Person person;

    @ManyToMany
    @JoinTable(name = "payment_installments",
               joinColumns = @JoinColumn(name = "payment_id"),
               inverseJoinColumns = @JoinColumn(name = "installment_id"))
    private List<Installment> installments;

    @ManyToOne
    private Title title;

}
