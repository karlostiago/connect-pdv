package com.util.commons.entity;

import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper = false)
@ExcludedCoverage
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TypeTitle extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String acronym;
}
