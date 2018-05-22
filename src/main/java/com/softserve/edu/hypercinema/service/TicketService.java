package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TicketService {

    void createTicket(TicketEntity ticketEntity);

    TicketEntity getTicket(Long id);

    TicketEntity getTicket(Long id, Authentication authentication);

    List<TicketEntity> getTickets();

    List<TicketEntity> getUnavailableTickets(Long sessionId);

    List<TicketEntity> getTickets(Authentication authentication);

    List<TicketEntity> getMyTickets(Authentication authentication);

    void updateTicket(Long id, TicketEntity ticketEntity);

    void deleteTicket(Long id);

    void sendMessage(TicketEntity ticketEntity);

    Page<TicketEntity> getTicketsByPage(Pageable pageable);

}
