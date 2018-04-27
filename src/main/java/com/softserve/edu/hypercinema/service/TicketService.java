package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.entity.TicketEntity;

import java.util.List;

public interface TicketService {

    void createTicket(TicketEntity ticketEntity);

    TicketEntity getTicket(Long id);

    List<TicketEntity> getTickets();

    void updateTicket(TicketEntity ticketEntity);

    void updateTicket(Long id, TicketEntity ticketEntity);

    void deleteTicket(Long id);

    void deleteTicket(TicketEntity ticketEntity);

    void generateTicket(TicketDto ticketDto);

    void updateTicket(Long id, TicketDto ticketDto);

}
