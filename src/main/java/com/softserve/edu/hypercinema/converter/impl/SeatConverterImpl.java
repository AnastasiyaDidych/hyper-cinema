package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.HallConverter;
import com.softserve.edu.hypercinema.converter.SeatConverter;
import com.softserve.edu.hypercinema.dto.SeatDto;
import com.softserve.edu.hypercinema.entity.SeatEntity;
import com.softserve.edu.hypercinema.service.HallService;
import com.softserve.edu.hypercinema.service.SeatService;
import com.softserve.edu.hypercinema.service.impl.HallServiceImpl;
import com.softserve.edu.hypercinema.service.impl.SeatServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeatConverterImpl implements SeatConverter {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HallConverter hallConverter;

    @Autowired
    private HallService hallService;

    @Override
    public SeatEntity convertToEntity(SeatDto seatDto) {

        SeatEntity seatEntity =  modelMapper.map(seatDto, SeatEntity.class);
        seatEntity.setId(seatDto.getId());
        seatEntity.setHall(hallService.getHall(seatDto.getHall_id()));
        seatEntity.setRow(seatDto.getRow());
        seatEntity.setNumber(seatDto.getNumber());
        seatEntity.setType(seatDto.getType());
        return seatEntity;


    }

    @Override
    public SeatDto convertToDto(SeatEntity seatEntity) {
        SeatDto seatDto = modelMapper.map(seatEntity, SeatDto.class);
        seatDto.setNumber(seatEntity.getNumber());
        seatDto.setRow(seatEntity.getRow());
        seatDto.setId(seatEntity.getId());
        seatDto.setType(seatEntity.getType());
        System.out.println(seatEntity.getHall());
        seatDto.setHall_id(seatEntity.getHall().getId());
        return seatDto;

    }

}
