package com.util.commons.dto;


import com.util.commons.enums.cash.CashType;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class CashDTO {

    private Long id;

    private String description;

    private BigDecimal openingValue;

    private BigDecimal totalValue;

    private BigDecimal closingValue;

    private BigDecimal entryValue;

    private BigDecimal exitValue;

    private LocalDate registerDate;

    private Date closingDate;

    private CashType types;

    private UserDTO user;

    private List<CashRegisterDTO> cashRegisters;

}
