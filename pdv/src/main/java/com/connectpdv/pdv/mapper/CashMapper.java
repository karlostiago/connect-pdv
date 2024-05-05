package com.connectpdv.pdv.mapper;

import com.util.commons.annotation.ExcludedCoverage;
import com.util.commons.dto.*;
import com.util.commons.entity.Address;
import com.util.commons.entity.Cash;
import com.util.commons.entity.Permission;
import com.util.commons.entity.Person;
import com.util.commons.entity.User;
import com.util.commons.entity.GroupsUser;
import com.util.commons.helper.DateHelper;
import com.util.commons.mapper.CollectionMapper;
import com.util.commons.mapper.DtoMapper;
import com.util.commons.mapper.EntityMapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@ExcludedCoverage
public class CashMapper implements EntityMapper<Cash, CashDTO>, DtoMapper<Cash, CashDTO> , CollectionMapper<Cash,CashDTO> {

    @Override
    public List<CashDTO> toList(List<Cash> entity) {
        return entity.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public CashDTO toDto(Cash entity) {
        CashDTO dto = new CashDTO();

        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setAccount(entity.getAccount());
        dto.setAgency(entity.getAgency());
        dto.setOpeningValue(entity.getOpeningValue());
        dto.setTotalValue(entity.getTotalValue());
        dto.setClosingDate(DateHelper.parseAtDateFrom(String.valueOf(entity.getClosingDate())));
        dto.setEntryValue(entity.getEntryValue());
        dto.setExitValue(entity.getExitValue());
        dto.setRegisterDate(DateHelper.parseAtLocalDateFrom(entity.getRegisterDate()));
        dto.setClosingDate(entity.getClosingDate());
        dto.setTypes(entity.getTypes());

        User user = entity.getUser();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setRegisterDate(user.getRegisterDate());

        Person person = user.getPerson();

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
        
        List<GroupsUserDTO> userGroupsDTO = user.getGroupsUsers().stream()
                .map(this::mapUserGroupToDTO)
                .toList();
        userDTO.setUserGroups(userGroupsDTO);
        
        List<PermissionDTO> permissionsDTO = user.getPermissions().stream()
                .map(this::mapPermissionToDTO)
                .toList();
        userDTO.setPermissions(permissionsDTO);

        dto.setUser(userDTO);
        
        personDTO.setAddress(addressDTO);
        userDTO.setPerson(personDTO);
        dto.setUser(userDTO);
        
        return dto;
    }

    @Override
    public Cash toEntity(CashDTO dto) {

        Person person = new Person();
        person.setId(dto.getUser().getPerson().getId());
        person.setDocument(dto.getUser().getPerson().getDocument());
        person.setDateRegister(dto.getUser().getPerson().getDateRegister());
        person.setName(dto.getUser().getPerson().getName());
        person.setContact(dto.getUser().getPerson().getContact());

        User user = new User();
        user.setId(dto.getId());
        user.setUserName(dto.getUser().getUserName());
        user.setRegisterDate(dto.getRegisterDate());

        Address address = getAddress(dto);

        Cash cash = getCash(dto, user);
        user.setPerson(person);
        person.setAddress(address);

        return cash;
    }

    private static Address getAddress(CashDTO dto) {
        Address address = new Address();
        address.setStreet(dto.getUser().getPerson().getAddress().getStreet());
        address.setDistrict(dto.getUser().getPerson().getAddress().getDistrict());
        address.setNumberHome(dto.getUser().getPerson().getAddress().getNumberHome());
        address.setZipCode(dto.getUser().getPerson().getAddress().getZipCode());
        address.setCity(dto.getUser().getPerson().getAddress().getZipCode());
        address.setUf(dto.getUser().getPerson().getAddress().getUf());
        return address;
    }

    private static Cash getCash(CashDTO dto, User user) {
        Cash cash = new Cash();

        cash.setId(dto.getId());
        cash.setDescription(dto.getDescription());
        cash.setOpeningValue(dto.getOpeningValue());
        cash.setTotalValue(dto.getTotalValue());
        cash.setClosingDate(dto.getClosingDate());
        cash.setEntryValue(dto.getEntryValue());
        cash.setExitValue(dto.getExitValue());
        cash.setRegisterDate(dto.getRegisterDate());
        cash.setClosingDate(dto.getClosingDate());
        cash.setTypes(dto.getTypes());
        cash.setAccount(dto.getAccount());
        cash.setAgency(dto.getAgency());
        cash.setUser(user);
        return cash;
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
