package com.util.commons.mapper;

public interface EntityMapper<T, D> extends Mapper {
    T toEntity(D dto);
}
