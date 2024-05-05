package com.util.commons.enums.cash;

import lombok.Getter;

@Getter
public enum CashType {
    CAIXA("CAIXA"), COFRE("COFRE"), BANCO("BANCO");

    private final String description;

    CashType(String description) {
        this.description = description;
    }

}