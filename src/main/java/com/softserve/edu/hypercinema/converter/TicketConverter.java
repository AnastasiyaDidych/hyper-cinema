package com.softserve.edu.hypercinema.converter;

import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.dto.TicketFullDto;
import com.softserve.edu.hypercinema.entity.TicketEntity;

import java.util.List;

public interface TicketConverter extends GenericConverter<TicketDto, TicketEntity> {

    TicketFullDto convertToFullDto(TicketEntity ticketEntity);

    TicketEntity convertFromFullDto(TicketFullDto fullTicket);

    List<TicketEntity> convertFromFullDtos(List<TicketFullDto> fullTickets);

    List<TicketFullDto> convertToFullDto(List<TicketEntity> ticketEntityList);

}
