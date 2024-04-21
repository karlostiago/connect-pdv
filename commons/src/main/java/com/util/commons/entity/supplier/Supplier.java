package com.util.commons.entity.supplier;

import com.util.commons.entity.address.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Supplier {

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
