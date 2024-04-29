package com.util.commons.entity;

import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = false)
@ExcludedCoverage
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PayableType extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registrationDate;
}

