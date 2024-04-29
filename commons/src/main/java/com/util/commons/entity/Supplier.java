package com.util.commons.entity;

import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = false)
@ExcludedCoverage
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Supplier extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fantasyName;

    private String name;

    @Column(unique = true)
    private String cnpj;

    private String stateRegistration;

    private int active;

    private String observation;

    private String phone;

    private Date registrationDate;

    @ManyToOne
    private Address address;
}
