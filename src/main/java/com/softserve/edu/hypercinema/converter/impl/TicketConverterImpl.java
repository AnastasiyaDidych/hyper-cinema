package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.TicketConverter;
import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.entity.TicketEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketConverterImpl implements TicketConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TicketEntity convertToEntity(TicketDto dto) {
        return modelMapper.map(dto, TicketEntity.class);
    }

    @Override
    public TicketDto convertToDto(TicketEntity entity) {
        return modelMapper.map(entity, TicketDto.class);
    }
}
