package com.softserve.edu.hypercinema.converter;

import com.softserve.edu.hypercinema.dto.BaseDto;
import com.softserve.edu.hypercinema.entity.BaseEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by VR
 * 23:01, 16.04.2018
 */

public interface GenericConverter<D extends BaseDto, E extends BaseEntity> {

    E convertToEntity(D dto);

    D convertToDto(E entity);

    default List<D> convertToDto(List<E> entities){
        return entities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    default List<E> convertToEntity(List<D> dtos){
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
