package com.util.commons.mapper;

import java.util.List;

public interface CollectionMapper <T, D> extends Mapper {

    List<D> toList(List<T> entity);
}
