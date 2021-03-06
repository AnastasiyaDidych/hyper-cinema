package com.softserve.edu.hypercinema.converter;

import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.dto.TicketFullDto;
import com.softserve.edu.hypercinema.entity.TicketEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TicketConverter extends GenericConverter<TicketFullDto, TicketEntity> {

    TicketEntity convertFromFullDto(TicketFullDto fullTicket);

    List<TicketFullDto> convertToFullDto(List<TicketEntity> ticketEntityList);

    Page<TicketFullDto> covertPageToFullDto(Page<TicketEntity> entityPages);

}
