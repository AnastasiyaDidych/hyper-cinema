package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.constants.CoefficientType;
import com.softserve.edu.hypercinema.entity.*;
import com.softserve.edu.hypercinema.exception.AccessViolationException;
import com.softserve.edu.hypercinema.exception.OrderNotFoundException;
import com.softserve.edu.hypercinema.exception.TicketNotFoundException;
import com.softserve.edu.hypercinema.exception.TicketUnavaiableException;
import com.softserve.edu.hypercinema.repository.TicketRepository;
import com.softserve.edu.hypercinema.service.MovieService;
import com.softserve.edu.hypercinema.service.SessionService;
import com.softserve.edu.hypercinema.service.TicketService;
import com.softserve.edu.hypercinema.service.UserService;
import com.softserve.edu.hypercinema.util.AuthUtil;
import com.softserve.edu.hypercinema.util.BarcodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class TicketServiceImpl implements TicketService {

    private static final String TICKET_NOT_FOUND_MESSAGE = "Could not find Ticket with id=";
    private static final String TICKET_ACCESS_VIOLATION_MESSAGE = "It is not your ticket";
    private static final String TICKET_UNAVAILABLE_MESSAGE = "This Seat is unavailable";

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Override
    public void createTicket(TicketEntity ticketEntity) {
        ticketEntity.setBarcode(BarcodeGenerator.generateStringBarcode(ticketEntity));
        setTicketCoefficients(ticketEntity);
        ticketRepository.save(validateTicket(ticketEntity));
    }

    @Override
    public TicketEntity getTicket(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(TICKET_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public TicketEntity getTicket(Long id, Authentication authentication) {
        if (!AuthUtil.isAdmin(authentication) || !AuthUtil.isManager(authentication)) {
            UserEntity userEntity = userService.getUser(authentication);
            boolean hasTicket = userEntity.getOrders().stream().anyMatch(order -> order.getTickets().stream().anyMatch(ticket -> ticket.getId().equals(id)));
            if (!hasTicket) {
                throw new AccessViolationException(TICKET_ACCESS_VIOLATION_MESSAGE);
            }
        }
        return ticketRepository.findById(id).orElseThrow(() ->
                new OrderNotFoundException(TICKET_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public List<TicketEntity> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<TicketEntity> getTickets(Authentication authentication) {
        if (!AuthUtil.isAdmin(authentication) || !AuthUtil.isManager(authentication)) {
            List<OrderEntity> orders = userService.getUser(authentication).getOrders();
            List<TicketEntity> tickets = new ArrayList<>();
            orders.forEach(order -> tickets.addAll(order.getTickets()));
            return tickets;
        }
        return ticketRepository.findAll();
    }

    @Override
    public void updateTicket(Long id, TicketEntity ticketEntity) {
        TicketEntity ticketEntity1 = getTicket(id);
        if (ticketEntity1 == null) {
            throw new TicketNotFoundException(TICKET_NOT_FOUND_MESSAGE + id);
        }
        ticketEntity.setId(id);
        ticketEntity.setOrder(ticketEntity1.getOrder());
        createTicket(ticketEntity);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    private TicketEntity validateTicket(TicketEntity ticketEntity) {
        List<TicketEntity> tickets = ticketRepository.findAllTicketBySessionIdAndSeatId(
                ticketEntity.getSession().getId(),
                ticketEntity.getSeat().getId());
        if (!tickets.isEmpty()) {
            throw new TicketUnavaiableException(TICKET_UNAVAILABLE_MESSAGE);
        }
        return ticketEntity;
    }

    public void setTicketCoefficients(TicketEntity ticket) {
        SessionEntity sessionEntity = ticket.getSession();
        MovieEntity movieEntity = movieService.getMovie(sessionEntity.getMovie().getId());
        LocalDate sessionDate = sessionEntity.getDate();
        SeatEntity seatEntity = ticket.getSeat();

        // TODO method, which returns List<CoefficientEntity>
        List<BigDecimal> coeffValues = sessionService.getCoefs(movieEntity, sessionDate, seatEntity);
        List<CoefficientEntity> ticketCoefficients = new ArrayList<>();

        for (BigDecimal coeff : coeffValues) {
            /* Do not write coefficient to DB if == 1 */
            if (!coeff.equals(CoefficientType.DEF.getValue())) {
                for (CoefficientType enumCoeff : CoefficientType.values()) {
                    if (coeff.equals(enumCoeff.getValue())) {
                        CoefficientEntity coefficientEntity = new CoefficientEntity();
                        coefficientEntity.setId(enumCoeff.getId());
                        ticketCoefficients.add(coefficientEntity);
                    }
                }
            }
        }
        ticket.setCoefficients(ticketCoefficients);
    }


}
