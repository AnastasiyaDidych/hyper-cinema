package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.MovieConverter;
import com.softserve.edu.hypercinema.dto.MovieDto;
import com.softserve.edu.hypercinema.entity.MovieEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieConverterImpl implements MovieConverter {

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
