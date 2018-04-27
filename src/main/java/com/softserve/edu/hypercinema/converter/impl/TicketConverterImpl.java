package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.TicketConverter;
import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.entity.OrderEntity;
import com.softserve.edu.hypercinema.entity.TicketEntity;
import lombok.experimental.var;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TicketConverterImpl implements TicketConverter {

    @Autowired
    private ModelMapper modelMapper;

//    @PostConstruct
//    public void init(){
//        TypeMap<TicketEntity, TicketDto> typeMap = modelMapper.createTypeMap(TicketEntity.class, TicketDto.class)
//        .addMappings(e -> {
//            e.map(ticketEntity -> ticketEntity.getSession().getId(), TicketDto::setSessionId);
//            e.map(ticketEntity -> ticketEntity.getSeat().getId(), TicketDto::setSeatId);
//            e.map(ticketEntity -> ticketEntity.getOrder().getId(), TicketDto::setOrderId);
//            });
//    }

    @Override
    public TicketEntity convertToEntity(TicketDto dto) {
        return modelMapper.map(dto, TicketEntity.class);
    }

    @Override
    public TicketDto convertToDto(TicketEntity entity) {
        return modelMapper.map(entity, TicketDto.class);
    }
}
