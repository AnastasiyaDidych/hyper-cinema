package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.GenericConverter;
import com.softserve.edu.hypercinema.dto.SeatDto;
import com.softserve.edu.hypercinema.entity.SeatEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeatConverter implements GenericConverter<SeatDto, SeatEntity> {


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SeatEntity convertToEntity(SeatDto seatDto) {
        return modelMapper.map(seatDto, SeatEntity.class);
    }

    @Override
    public SeatDto convertToDto(SeatEntity seatEntity) {
        return modelMapper.map(seatEntity, SeatDto.class);
    }

}
