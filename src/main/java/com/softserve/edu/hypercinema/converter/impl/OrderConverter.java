package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.GenericConverter;
import com.softserve.edu.hypercinema.dto.OrderDto;
import com.softserve.edu.hypercinema.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter implements GenericConverter<OrderDto, OrderEntity> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderEntity convertToEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, OrderEntity.class);
    }

    @Override
    public OrderDto convertToDto(OrderEntity orderEntity) {
        return modelMapper.map(orderEntity, OrderDto.class);
    }
}
