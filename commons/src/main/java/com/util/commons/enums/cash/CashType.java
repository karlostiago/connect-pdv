package com.util.commons.enums.cash;

import lombok.Getter;

@Getter
public enum CashType {
    CASH("Caixa"), SAFE("Cofre"), BANK("Banco");

    private final String description;

    CashType(String description) {
        this.description = description;
    }

}