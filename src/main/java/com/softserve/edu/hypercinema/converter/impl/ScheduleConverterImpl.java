package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.ScheduleConverter;
import com.softserve.edu.hypercinema.dto.ScheduleDto;
import com.softserve.edu.hypercinema.entity.ScheduleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConverterImpl implements ScheduleConverter {
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ScheduleEntity convertToEntity(ScheduleDto dto) {
        return modelMapper.map(dto,ScheduleEntity.class);
    }

    @Override
    public ScheduleDto convertToDto(ScheduleEntity entity) {
        return modelMapper.map(entity,ScheduleDto.class);
    }
}
