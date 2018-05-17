package com.softserve.edu.hypercinema.converter.impl;

import com.softserve.edu.hypercinema.converter.TicketConverter;
import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.dto.TicketFullDto;
import com.softserve.edu.hypercinema.entity.TicketEntity;
import com.softserve.edu.hypercinema.service.SeatService;
import com.softserve.edu.hypercinema.service.SessionService;
import com.softserve.edu.hypercinema.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketConverterImpl implements TicketConverter {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TicketService ticketService;

    @Override
    public TicketEntity convertToEntity(TicketDto dto) {
        TicketEntity ticketEntity = modelMapper.map(dto, TicketEntity.class);
        ticketEntity.setSession(sessionService.getSession(dto.getSessionId()));
        ticketEntity.setSeat(seatService.getSeat(dto.getSeatId()));
//        ticketEntity.setPrice(dto.getPrice());
        return ticketEntity;
    }

    @Override
    public TicketDto convertToDto(TicketEntity entity) {
        TicketDto ticketDto = modelMapper.map(entity, TicketDto.class);
        ticketDto.setSessionId(entity.getSession().getId());
        ticketDto.setSeatId(entity.getSeat().getId());
        return ticketDto;
    }

    @Override
    public TicketFullDto convertToFullDto(TicketEntity ticketEntity) {
        TicketFullDto ticketFullDto = modelMapper.map(ticketEntity, TicketFullDto.class);
        ticketFullDto.setFilmName(ticketEntity.getSession().getMovie().getTitle());
        ticketFullDto.setTech(ticketEntity.getSession().getHall().getTech());
        ticketFullDto.setSessionDate(ticketEntity.getSession().getDate());
        ticketFullDto.setSessionTime(ticketEntity.getSession().getStartTime());
        ticketFullDto.setSeatRow(ticketEntity.getSeat().getRow());
        ticketFullDto.setSeatNumber(ticketEntity.getSeat().getNumber());
        ticketFullDto.setHallName(ticketEntity.getSession().getHall().getName());
        ticketFullDto.setBarcode(ticketEntity.getBarcode());
        return ticketFullDto;
    }

    @Override
    public TicketEntity convertFromFullDto(TicketFullDto fullTicket) {
        return ticketService.getTicket(fullTicket.getId());
    }


    @Override
    public List<TicketFullDto> convertToFullDto(List<TicketEntity> ticketEntityList) {
        return ticketEntityList.stream().map(this::convertToFullDto).collect(Collectors.toList());
    }
}
