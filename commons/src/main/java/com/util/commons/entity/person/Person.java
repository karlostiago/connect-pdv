package com.util.commons.entity.person;
import com.util.commons.entity.address.Address;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String document;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateRegister;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String name;

    private String nick;

    private String observation;

    private String contact;
}
