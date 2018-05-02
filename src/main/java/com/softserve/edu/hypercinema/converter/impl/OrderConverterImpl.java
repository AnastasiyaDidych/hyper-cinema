package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.OrderConverter;
import com.softserve.edu.hypercinema.converter.TicketConverter;
import com.softserve.edu.hypercinema.dto.OrderDto;
import com.softserve.edu.hypercinema.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverterImpl implements OrderConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TicketConverter ticketConverter;

    @Override
    public OrderEntity convertToEntity(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setPending(orderDto.isPending());
        orderEntity.setConfirmed(orderDto.isConfirmed());
        orderEntity.setTickets(ticketConverter.convertToEntity(orderDto.getTickets()));
        return orderEntity;
    }

    @Override
    public OrderDto convertToDto(OrderEntity orderEntity) {
        return modelMapper.map(orderEntity, OrderDto.class);
    }
}
