package com.connectpdv.pdv.mapper;

import com.util.commons.annotation.ExcludedCoverage;
import com.util.commons.dto.*;
import com.util.commons.entity.User;
import com.util.commons.mapper.CollectionMapper;
import com.util.commons.mapper.DtoMapper;
import com.util.commons.mapper.EntityMapper;
import org.springframework.stereotype.Component;
import com.util.commons.dto.UserDTO;
import com.util.commons.entity.User;
import com.util.commons.entity.GroupsUser;
import com.util.commons.entity.Permission;
import com.util.commons.entity.Person;
import com.util.commons.entity.Address;
import com.util.commons.mapper.CollectionMapper;
import com.util.commons.mapper.DtoMapper;
import com.util.commons.mapper.EntityMapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@ExcludedCoverage
public class UserMapper implements EntityMapper<User, UserDTO>, DtoMapper<User, UserDTO> , CollectionMapper<User,UserDTO> {
    @Override
    public List<UserDTO> toList(List<User> entity) {
        return entity.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public UserDTO toDto(User entity) {
        UserDTO dto = new UserDTO();

        dto.setId(entity.getId());
        dto.setUserName(entity.getUserName());
        dto.setRegisterDate(entity.getRegisterDate());

        Person person = entity.getPerson();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setDocument(person.getDocument());
        personDTO.setDateRegister(person.getDateRegister());
        personDTO.setName(person.getName());
        personDTO.setContact(person.getContact());

        Address address = person.getAddress();
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(address.getStreet());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setNumberHome(address.getNumberHome());
        addressDTO.setZipCode(address.getZipCode());
        addressDTO.setCity(address.getCity());
        addressDTO.setUf(address.getUf());

        List<GroupsUserDTO> userGroupsDTO = entity.getGroupsUsers().stream()
                .map(this::mapUserGroupToDTO)
                .toList();
        dto.setUserGroups(userGroupsDTO);

        List<PermissionDTO> permissionsDTO = entity.getPermissions().stream()
                .map(this::mapPermissionToDTO)
                .toList();
        dto.setPermissions(permissionsDTO);

        dto.setPerson(personDTO);
        personDTO.setAddress(addressDTO);

        return dto;
    }

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUserName(dto.getUserName());
        user.setRegisterDate(dto.getRegisterDate());

        PersonDTO personDTO = dto.getPerson();
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setDocument(personDTO.getDocument());
        person.setDateRegister(personDTO.getDateRegister());
        person.setName(personDTO.getName());
        person.setContact(personDTO.getContact());

        AddressDTO addressDTO = personDTO.getAddress();
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setDistrict(addressDTO.getDistrict());
        address.setNumberHome(addressDTO.getNumberHome());
        address.setZipCode(addressDTO.getZipCode());
        address.setCity(addressDTO.getCity());
        address.setUf(addressDTO.getUf());

        person.setAddress(address);

        user.setPerson(person);

        List<GroupsUserDTO> groupsUserDTOList = dto.getUserGroups();
        List<GroupsUser> groupsUserList = groupsUserDTOList.stream()
                .map(groupsUserDTO -> {
                    GroupsUser groupsUser = new GroupsUser();
                    groupsUser.setId(groupsUserDTO.getId());
                    groupsUser.setGroupName(groupsUserDTO.getGroupName());
                    return groupsUser;
                })
                .toList();
        user.setGroupsUsers(groupsUserList);

        List<PermissionDTO> permissionDTOList = dto.getPermissions();
        List<Permission> permissionList = permissionDTOList.stream()
                .map(permissionDTO -> {
                    Permission permission = new Permission();
                    permission.setId(permissionDTO.getId());
                    permission.setName(permissionDTO.getName());
                    permission.setDescription(permissionDTO.getDescription());
                    return permission;
                })
                .toList();
        user.setPermissions(permissionList);

        return user;
    }

    private GroupsUserDTO mapUserGroupToDTO(GroupsUser userGroup) {
        GroupsUserDTO userGroupDTO = new GroupsUserDTO();
        userGroupDTO.setId(userGroup.getId());
        userGroupDTO.setGroupName(userGroup.getGroupName());

        return userGroupDTO;
    }

    private PermissionDTO mapPermissionToDTO(Permission permission) {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setId(permission.getId());
        permissionDTO.setName(permission.getName());
        permissionDTO.setDescription(permission.getDescription());

        return permissionDTO;
    }
}
