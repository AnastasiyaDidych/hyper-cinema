package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.TicketEntity;

import java.util.List;

public interface TicketService {

    void createTicket(TicketEntity ticketEntity);

    TicketEntity getTicket(Long id);

    List<TicketEntity> selectAllTickets();

    void updateTicket(TicketEntity ticketEntity);

    void deleteTicketById(Long id);

    void deleteTicket(TicketEntity ticketEntity);

}
