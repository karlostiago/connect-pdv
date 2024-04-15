package com.util.commons.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "representative")
@Getter
@Setter
public class Representative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
