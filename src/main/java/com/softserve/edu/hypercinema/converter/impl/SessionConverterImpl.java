package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.SessionConverter;
import com.softserve.edu.hypercinema.dto.SessionDto;
import com.softserve.edu.hypercinema.entity.SessionEntity;
import com.softserve.edu.hypercinema.service.SessionService;
import com.softserve.edu.hypercinema.service.impl.SeatServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SessionConverterImpl implements SessionConverter {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ModelMapper modelMapper;

//    @PostConstruct
//    public void init(){
//        TypeMap<SessionEntity, SessionDto> typeMap = modelMapper.createTypeMap(SessionEntity.class, SessionDto.class)
//                .addMappings(e -> {
//                    e.map(sessionEntity -> sessionService.getBasePrice(sessionEntity), SessionDto::setBasePrice);
//                    e.map(sessionEntity -> sessionService.getVipPrice(sessionEntity), SessionDto::setVipPrice);
//                });
//    }

    @Override
    public SessionEntity convertToEntity(SessionDto dto) {
        return modelMapper.map(dto,SessionEntity.class);
    }

    @Override
    public SessionDto convertToDto(SessionEntity entity) {
        SessionDto sessionDto = modelMapper.map(entity,SessionDto.class);
        sessionDto.setMovieId(entity.getMovie().getId());
        sessionDto.setHallId(entity.getHall().getId());
        sessionDto.setBasePrice(sessionService.getBasePrice(entity));
        sessionDto.setVipPrice(sessionService.getVipPrice(entity));
        return sessionDto;
    }


}
