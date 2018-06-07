package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.OrderConverter;
import com.softserve.edu.hypercinema.converter.TicketConverter;
import com.softserve.edu.hypercinema.dto.OrderDto;
import com.softserve.edu.hypercinema.entity.OrderEntity;
import com.softserve.edu.hypercinema.entity.TicketEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderConverterImpl implements OrderConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TicketConverter ticketConverter;

    @Override
    public OrderEntity convertToEntity(OrderDto orderDto) {
        System.out.println("orderdto " + orderDto);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setPended(orderDto.isPended());
        orderEntity.setConfirmed(orderDto.isConfirmed());
        orderEntity.setOrderNumber(orderDto.getOrderNumber());
        orderEntity.setTickets(ticketConverter.convertToEntity(orderDto.getTickets()));
        return orderEntity;
    }

    @Override
    public OrderDto convertToDto(OrderEntity orderEntity) {
        OrderDto orderDto = modelMapper.map(orderEntity, OrderDto.class);
        orderDto.setOrderDate(orderEntity.getOrderDate());
        orderDto.setOrderNumber(orderEntity.getOrderNumber());
        orderDto.setTickets(ticketConverter.convertToDto(orderEntity.getTickets()));
        BigDecimal price = BigDecimal.ZERO;
        for (TicketEntity ticketEntity : orderEntity.getTickets()) {
            price = price.add(ticketEntity.getPrice());
        }
        orderDto.setOrderTotal(price);
        return orderDto;
    }
}
