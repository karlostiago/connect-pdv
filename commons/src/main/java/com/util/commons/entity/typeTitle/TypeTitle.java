package com.util.commons.entity.typeTitle;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TypeTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String acronym;
}
