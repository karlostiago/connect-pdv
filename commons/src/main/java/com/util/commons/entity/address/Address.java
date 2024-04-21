package com.util.commons.entity.address;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Data
public class Address {

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
