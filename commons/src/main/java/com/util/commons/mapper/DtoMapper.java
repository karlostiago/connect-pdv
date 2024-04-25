package com.util.commons.mapper;


public interface DtoMapper<T, D> extends Mapper {

    D toDto(T entity);
}
