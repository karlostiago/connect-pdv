package com.util.commons.enums.balance;

import lombok.Getter;

@Getter
public enum BalanceType {
    Money ("Dinheiro"),
    CHECK("Cheque"),
    ADVANCE("Adiantamento"),
    VALE_EXCHANGE("Troca"),
    CARD("Cartao");

    private final String description;

     BalanceType(String description) {
         this.description = description;
    }
}
