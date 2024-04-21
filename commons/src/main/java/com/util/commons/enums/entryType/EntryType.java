package com.util.commons.enums.entryType;

import lombok.Getter;

@Getter
public enum EntryType {
    SUPPLY("Suprimento"),
    BLEED("Sangria"),
    INITIAL_BALANCE("Saldo Inicial"),
    RECEIPT("Recebimento"),
    TRANSFER("Transferência"),
    PAYMENT("Pagamento");

    private final String description;

    EntryType(String description) {
        this.description = description;
    }

}