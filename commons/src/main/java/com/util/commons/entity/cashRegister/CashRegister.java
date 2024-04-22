package com.util.commons.entity.cashRegister;

import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import com.util.commons.entity.cash.Cash;
import com.util.commons.entity.payableInstallment.PayableInstallment;
import com.util.commons.entity.receipt.Receipt;
import com.util.commons.entity.user.User;
import com.util.commons.enums.entryStyle.EntryStyle;
import com.util.commons.enums.entryType.EntryType;
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
public class CashRegister extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String observation;

    @NumberFormat(pattern = "##,##0.00")
    private BigDecimal totalValue = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private EntryType entryType;

    @Enumerated(EnumType.STRING)
    private EntryStyle entryStyle;

    @ManyToOne
    private Cash cash;

    @ManyToOne
    private User user;

    @OneToOne
    private Receipt receipt;

    @ManyToOne
    private PayableInstallment payableInstallment;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDate registerDate;

}
