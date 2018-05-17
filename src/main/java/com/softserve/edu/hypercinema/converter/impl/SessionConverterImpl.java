package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.SessionConverter;
import com.softserve.edu.hypercinema.dto.SessionDto;
import com.softserve.edu.hypercinema.entity.SessionEntity;
import com.softserve.edu.hypercinema.service.MovieService;
import com.softserve.edu.hypercinema.service.SessionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionConverterImpl implements SessionConverter {


    @Autowired
    private SessionService sessionService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovieService movieService;

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
        sessionDto.setTitle(movieService.getMovie(sessionDto.getMovieId()).getTitle());
        return sessionDto;
    }


}
