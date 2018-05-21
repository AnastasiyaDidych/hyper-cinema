package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.TicketEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TicketService {

    void createTicket(TicketEntity ticketEntity);

    TicketEntity getTicket(Long id);

    TicketEntity getTicket(Long id, Authentication authentication);

    List<TicketEntity> getTickets();

    List<TicketEntity> getUnavailableTickets(Long sessionId);

    List<TicketEntity> getTickets(Authentication authentication);

    void updateTicket(Long id, TicketEntity ticketEntity);

    void deleteTicket(Long id);

}
