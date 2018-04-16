package com.softserve.ua.convertorImpl;

import com.softserve.ua.convertor.MovieConverter;
import com.softserve.ua.dto.MovieDto;
import com.softserve.ua.entity.MovieEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConvertorImpl implements MovieConverter {

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public MovieEntity convertToEntity(MovieDto dto) {
        return modelMapper.map(dto,MovieEntity.class);
    }

    @Override
    public MovieDto convertToDto(MovieEntity entity) {
        return modelMapper.map(entity,MovieDto.class);
    }
}
