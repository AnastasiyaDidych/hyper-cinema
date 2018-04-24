package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.GenericConverter;
import com.softserve.edu.hypercinema.dto.CoefficientDto;
import com.softserve.edu.hypercinema.entity.CoefficientEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoefficientConverter implements GenericConverter<CoefficientDto, CoefficientEntity> {

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CoefficientEntity convertToEntity(CoefficientDto dto) {
        return modelMapper.map(dto, CoefficientEntity.class);
    }

    @Override
    public CoefficientDto convertToDto(CoefficientEntity entity) {
        return modelMapper.map(entity, CoefficientDto.class);
    }
}
