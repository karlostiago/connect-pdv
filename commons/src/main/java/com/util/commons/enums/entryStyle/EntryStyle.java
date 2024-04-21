package com.util.commons.enums.entryStyle;

import lombok.Getter;

@Getter
public enum EntryStyle {

    INCOME("Entrada"),
    OUTGOING("Saída");

    private final String description;

    EntryStyle(String description) {
        this.description = description;
    }

}