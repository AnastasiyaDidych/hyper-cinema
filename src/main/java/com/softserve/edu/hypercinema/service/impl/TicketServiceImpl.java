package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.constants.CoefficientType;
import com.softserve.edu.hypercinema.entity.*;
import com.softserve.edu.hypercinema.exception.TicketNotFoundException;
import com.softserve.edu.hypercinema.repository.TicketRepository;
import com.softserve.edu.hypercinema.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private MovieService movieService;

    @Override
    public void createTicket(TicketEntity ticketEntity) {
        setTicketCoefficients(ticketEntity);
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
    public void updateTicket(Long id, TicketEntity ticketEntity) {
        if (getTicket(id) != null) {
            ticketEntity.setId(id);
            ticketRepository.save(ticketEntity);
        } else {
            throw new TicketNotFoundException(TICKET_NOT_FOUND_MESSAGE + id);
        }
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public void setTicketCoefficients(TicketEntity ticket) {
        SessionEntity sessionEntity = ticket.getSession();
        MovieEntity movieEntity = movieService.getMovie(sessionEntity.getMovie().getId());
        LocalDate sessionDate = sessionEntity.getDate();
        SeatEntity seatEntity = ticket.getSeat();

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
