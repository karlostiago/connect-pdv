package com.util.commons.entity.person;
import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import com.util.commons.entity.address.Address;
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
public class Person extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String document;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateRegister;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private String name;

    private String nick;

    private String observation;

    private String contact;
}
