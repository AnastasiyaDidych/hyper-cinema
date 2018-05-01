package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.dto.SessionDto;
import com.softserve.edu.hypercinema.entity.HallEntity;

import com.softserve.edu.hypercinema.entity.MovieEntity;
import com.softserve.edu.hypercinema.entity.SessionEntity;
import com.softserve.edu.hypercinema.entity.TicketEntity;
import com.softserve.edu.hypercinema.repository.SessionRepository;
import com.softserve.edu.hypercinema.repository.TicketRepository;
import com.softserve.edu.hypercinema.service.HallService;
import com.softserve.edu.hypercinema.service.MovieService;
import com.softserve.edu.hypercinema.service.SessionService;
import com.softserve.edu.hypercinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    private final String MOVIE_ALREADY_EXISTS_MESSAGE = "movie already exist";
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private TicketService ticketService;


    @Override
    public SessionEntity getSession(Long id) {
        return sessionRepository.findById(id).orElse(null);
    }

    @Override
    public void createSession(SessionEntity sessionEntity) {
            sessionRepository.save(sessionEntity);
    }

    @Override
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }

    @Override
    public void updateSession(SessionEntity sessionEntity) {
        sessionRepository.save(sessionEntity);
    }

    @Override
    public List<SessionEntity> getSessions() {
        return sessionRepository.findAll();
    }

    public void generateSession(MovieEntity movieEntity, HallEntity hallEntity,
                                String date, String time)   {

         SessionEntity sessionEntity = new SessionEntity();
         sessionEntity.setMovie(movieEntity);
         sessionEntity.setHall(hallEntity);
         sessionEntity.setDate(LocalDate.parse(date));
         LocalTime startTime = LocalTime.parse(time);
         sessionEntity.setStartTime(startTime);
         sessionEntity.setEndTime(startTime.plusMinutes(movieEntity.getDuration()+15));
         generateTicketsForSession(sessionEntity);
         createSession(sessionEntity);

    }


    private void generateTicketsForSession(SessionEntity sessionEntity) {
        for (int i = 0; i <= sessionEntity.getHall().getCapacity(); i++) {
            TicketEntity ticketEntity = new TicketEntity();
            ticketEntity.setSession(sessionEntity);
            sessionEntity.getTickets().add(ticketEntity);

            //ticketRepository.save(ticketEntity);
            ticketService.createTicket(ticketEntity);
        }

    }



}

