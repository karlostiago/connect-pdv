package com.util.commons.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserDTO {

    private Long id;

    private String userName;

    private LocalDate registerDate;

    private PersonDTO person;
}
