package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.entity.TicketEntity;
import com.softserve.edu.hypercinema.exception.TicketNotFoundException;
import com.softserve.edu.hypercinema.repository.TicketRepository;
import com.softserve.edu.hypercinema.service.OrderService;
import com.softserve.edu.hypercinema.service.SeatService;
import com.softserve.edu.hypercinema.service.SessionService;
import com.softserve.edu.hypercinema.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class TicketServiceImpl implements TicketService {

    private static final String TICKET_NOT_FOUND_MESSAGE = "Could not find Ticket with id=";

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private OrderService orderService;

    @Override
    public void createTicket(TicketEntity ticketEntity) {
        ticketRepository.save(ticketEntity);
    }

    @Override
    public TicketEntity getTicket(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(TICKET_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public List<TicketEntity> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public void updateTicket(TicketEntity ticketEntity) {
        ticketRepository.save(ticketEntity);
    }

    @Override
    public void updateTicket(Long id, TicketEntity ticketEntity) {
        ticketEntity.setId(id);
        ticketRepository.save(ticketEntity);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public void deleteTicket(TicketEntity ticketEntity) {
        ticketRepository.delete(ticketEntity);
    }



    @Override
    public void generateTicket(TicketDto ticketDto) {
        ticketRepository.save(buildTicketEntity(ticketDto));
    }

    @Override
    public void updateTicket(Long id, TicketDto ticketDto) {
        if (getTicket(id) != null){
            TicketEntity ticketEntity = buildTicketEntity(ticketDto);
            ticketEntity.setId(id);
            ticketRepository.save(ticketEntity);
        }
    }

    @Override
    public TicketEntity buildTicketEntity(TicketDto ticketDto){
       return TicketEntity.builder()
//                .order(orderService.getOrder(ticketDto.getOrderId()))
                .session(sessionService.getSession(ticketDto.getSessionId()))
                .seat(seatService.getSeat(ticketDto.getSeatId()))
                .price(ticketDto.getPrice())
                .build();
    }

}
