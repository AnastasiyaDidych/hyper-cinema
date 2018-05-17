package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.ScheduleConverter;
import com.softserve.edu.hypercinema.dto.ScheduleDto;
import com.softserve.edu.hypercinema.entity.SessionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConverterImpl implements ScheduleConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SessionEntity convertToEntity(ScheduleDto dto) {
        return modelMapper.map(dto,SessionEntity.class);
    }

    @Override
    public ScheduleDto convertToDto(SessionEntity entity) {
        ScheduleDto scheduleDto = modelMapper.map(entity,ScheduleDto.class);
        scheduleDto.setId(entity.getId());
        scheduleDto.setLocalTime(entity.getStartTime());
        return scheduleDto;
    }
}
