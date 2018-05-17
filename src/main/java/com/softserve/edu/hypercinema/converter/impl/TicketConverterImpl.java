package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.TicketConverter;
import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.entity.TicketEntity;
import com.softserve.edu.hypercinema.service.SeatService;
import com.softserve.edu.hypercinema.service.SessionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketConverterImpl implements TicketConverter {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TicketEntity convertToEntity(TicketDto dto) {
        TicketEntity ticketEntity = modelMapper.map(dto, TicketEntity.class);
        ticketEntity.setSession(sessionService.getSession(dto.getSessionId()));
        ticketEntity.setSeat(seatService.getSeat(dto.getSeatId()));
        ticketEntity.setPrice(dto.getPrice());
        return ticketEntity;
    }

    @Override
    public TicketDto convertToDto(TicketEntity entity) {
        TicketDto ticketDto = modelMapper.map(entity, TicketDto.class);
        ticketDto.setSessionId(entity.getSession().getId());
        ticketDto.setSeatId(entity.getSeat().getId());
        return ticketDto;
    }
}
