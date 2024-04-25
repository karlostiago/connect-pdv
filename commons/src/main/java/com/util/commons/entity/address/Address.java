package com.util.commons.entity.address;

import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = false)
@ExcludedCoverage
@Data
public class Address extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registerDate;

    private String street;

    private String district;

    private String numberHome;

    private String zipCode;

    private String reference;

    private String city;

    private String uf;

}
