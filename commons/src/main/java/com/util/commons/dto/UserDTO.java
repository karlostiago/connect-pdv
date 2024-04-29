package com.util.commons.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserDTO {

    private Long id;

    private String userName;

    private LocalDate registerDate;

    private PersonDTO person;

    private List<GroupsUserDTO> userGroups;

    private List<PermissionDTO> permissions;
}
