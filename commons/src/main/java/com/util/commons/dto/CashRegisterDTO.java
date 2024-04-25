package com.util.commons.dto;

import com.util.commons.enums.entryStyle.EntryStyle;
import com.util.commons.enums.entryType.EntryType;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CashRegisterDTO {

    private Long id;

    private String observation;

    private BigDecimal totalValue;

    private EntryType entryType;

    private EntryStyle entryStyle;

    private CashDTO cash;

    private UserDTO user;

    private LocalDate registerDate;
}
