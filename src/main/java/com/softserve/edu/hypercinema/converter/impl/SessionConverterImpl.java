package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.SessionConverter;
import com.softserve.edu.hypercinema.dto.SessionDto;
import com.softserve.edu.hypercinema.entity.SessionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionConverterImpl implements SessionConverter {

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public SessionEntity convertToEntity(SessionDto dto) {
        return modelMapper.map(dto,SessionEntity.class);
    }

    @Override
    public SessionDto convertToDto(SessionEntity entity) {
        return modelMapper.map(entity,SessionDto.class);
    }


}
