package com.softserve.edu.hypercinema.converter.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.softserve.edu.hypercinema.converter.UserConverter;
import com.softserve.edu.hypercinema.dto.UserDto;
import com.softserve.edu.hypercinema.entity.UserEntity;

@Component
public class UserConverterImpl implements UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserEntity convertToEntity(UserDto dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

    @Override
    public UserDto convertToDto(UserEntity entity) {
        return modelMapper.map(entity, UserDto.class);
    }

}
