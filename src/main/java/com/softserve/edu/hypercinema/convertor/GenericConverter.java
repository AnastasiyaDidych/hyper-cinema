package com.softserve.ua.convertor;

import com.softserve.ua.dto.BaseDto;
import com.softserve.ua.entity.BaseEntity;

import java.util.List;
import java.util.stream.Collectors;

public interface GenericConverter<D extends BaseDto, E extends BaseEntity> {

    E convertToEntity(D dto);
    D convertToDto(E entity);

    default List<D> convertToDto(List<E> entityList) {
        return entityList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    default List<E> convertToEntity(List<D> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
