package com.util.commons.entity.cashRegister;

import com.util.commons.entity.cash.Cash;
import com.util.commons.entity.payableInstallment.PayableInstallment;
import com.util.commons.entity.receipt.Receipt;
import com.util.commons.entity.user.User;
import com.util.commons.enums.entryStyle.EntryStyle;
import com.util.commons.enums.entryType.EntryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CashRegister {

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
