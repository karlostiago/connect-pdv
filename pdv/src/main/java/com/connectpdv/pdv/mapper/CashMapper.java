package com.connectpdv.pdv.mapper;

import com.util.commons.annotation.ExcludedCoverage;
import com.util.commons.dto.*;
import com.util.commons.entity.address.Address;
import com.util.commons.entity.cash.Cash;
import com.util.commons.entity.person.Person;
import com.util.commons.entity.user.User;
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
        dto.setOpeningValue(entity.getOpeningValue());
        dto.setTotalValue(entity.getTotalValue());
        dto.setClosingDate(entity.getClosingDate());
        dto.setEntryValue(entity.getEntryValue());
        dto.setExitValue(entity.getExitValue());
        dto.setRegisterDate(entity.getRegisterDate());
        dto.setClosingDate(entity.getClosingDate());
        dto.setTypes(entity.getTypes());

        User user = entity.getUser();
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setRegisterDate(user.getRegisterDate());

            Person person = user.getPerson();
            if (person != null) {
                PersonDTO personDTO = new PersonDTO();
                personDTO.setId(person.getId());
                personDTO.setDocument(person.getDocument());
                personDTO.setDateRegister(person.getDateRegister());
                personDTO.setName(person.getName());
                personDTO.setContact(person.getContact());

                Address address = person.getAddress();
                if (address != null) {
                    AddressDTO addressDTO = new AddressDTO();
                    addressDTO.setStreet(address.getStreet());
                    addressDTO.setDistrict(address.getDistrict());
                    addressDTO.setNumberHome(address.getNumberHome());
                    addressDTO.setZipCode(address.getZipCode());
                    addressDTO.setCity(address.getCity());
                    addressDTO.setUf(address.getUf());

                    personDTO.setAddress(addressDTO);
                }

                userDTO.setPerson(personDTO);
            }

            dto.setUser(userDTO);
        }

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

        Address address = new Address();
        address.setStreet(dto.getUser().getPerson().getAddress().getStreet());
        address.setDistrict(dto.getUser().getPerson().getAddress().getDistrict());
        address.setNumberHome(dto.getUser().getPerson().getAddress().getNumberHome());
        address.setZipCode(dto.getUser().getPerson().getAddress().getZipCode());
        address.setCity(dto.getUser().getPerson().getAddress().getZipCode());
        address.setUf(dto.getUser().getPerson().getAddress().getUf());

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

        cash.setUser(user);
        user.setPerson(person);
        person.setAddress(address);

        return cash;
    }
}