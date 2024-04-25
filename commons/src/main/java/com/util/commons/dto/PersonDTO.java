package com.util.commons.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PersonDTO {

    private Long id;

    private String document;

    private Date dateRegister;

    private AddressDTO address;

    private String name;

    private String contact;
}
