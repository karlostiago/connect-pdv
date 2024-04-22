package com.util.commons.enums.balance;

import lombok.Getter;

@Getter
public enum BalanceType {
    MONEY("Dinheiro"),
    CHECK("Cheque"),
    ADVANCE("Adiantamento"),
    VALE_EXCHANGE("Troca"),
    CARD("Cartao"),
    PIX("PIX");

    private final String description;

     BalanceType(String description) {
         this.description = description;
    }
}
