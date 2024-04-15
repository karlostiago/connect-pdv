package com.util.commons.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class RepresentativeDTO {
    private boolean active;
    private boolean allowsSelling;
    private boolean releaseExcessCredit;
    private boolean exemptsInterestFine;
    private String login;
    private String name;
    private String profile;
    private String nickname;
    private BigDecimal maximumDiscount;
    private BigDecimal excessCreditValue;
}
