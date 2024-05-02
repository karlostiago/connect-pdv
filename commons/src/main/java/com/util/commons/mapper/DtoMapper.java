package com.util.commons.mapper;


import java.text.ParseException;

public interface DtoMapper<T, D> extends Mapper {

    D toDto(T entity);
}
