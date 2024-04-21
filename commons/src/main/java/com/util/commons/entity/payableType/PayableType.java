package com.util.commons.entity.payableType;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Data
public class PayableType  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registrationDate;
}

