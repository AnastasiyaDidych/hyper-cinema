package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.constants.CoefficientType;
import com.softserve.edu.hypercinema.constants.SeatStatus;
import com.softserve.edu.hypercinema.dto.SessionDto;
import com.softserve.edu.hypercinema.entity.*;
import com.softserve.edu.hypercinema.repository.SessionRepository;
import com.softserve.edu.hypercinema.service.HallService;
import com.softserve.edu.hypercinema.service.MovieService;
import com.softserve.edu.hypercinema.service.SessionService;
import com.softserve.edu.hypercinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    private final MathContext mc = new MathContext(3);

    private final String MOVIE_ALREADY_EXISTS_MESSAGE = "movie already exist";
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private HallService hallService;

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
                                String date, String time) {

        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setMovie(movieEntity);
        sessionEntity.setHall(hallEntity);
        sessionEntity.setDate(LocalDate.parse(date));
        LocalTime startTime = LocalTime.parse(time);
        sessionEntity.setStartTime(startTime);
        sessionEntity.setEndTime(startTime.plusMinutes(movieEntity.getDuration() + 15));
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

    @Override
    public void generateSession(SessionDto sessionDto) {

        SessionEntity sessionEntity = new SessionEntity();
        MovieEntity movieEntity = movieService.getMovie(sessionDto.getMovieId());
        sessionEntity.setMovie(movieEntity);
        sessionEntity.setHall(hallService.getHall(sessionDto.getHallId()));
        sessionEntity.setDate(LocalDate.parse(sessionDto.getDate(), DATE_FORMAT));
        LocalTime startTime = LocalTime.parse(sessionDto.getStartTime(), TIME_FORMATTER);
        sessionEntity.setStartTime(startTime);
        sessionEntity.setEndTime(startTime.plusMinutes(movieEntity.getDuration() + 15));
        //generateTicketsForSession(sessionEntity);
        createSession(sessionEntity);


    }

    @Override
    public BigDecimal getBasePrice(SessionEntity sessionEntity) {
        MovieEntity movieEntity = sessionEntity.getMovie();
        LocalDate sessionDay = sessionEntity.getDate();
        List<BigDecimal> coefs = new ArrayList<>();

        coefs.add(getPremierCoef(movieEntity, sessionDay));
        coefs.add(getEndRentCoef(movieEntity, sessionDay));
        coefs.add(CoefficientType.BASE.getValue());

        BigDecimal total = movieEntity.getPrice();
        for (BigDecimal coef: coefs) {
            total = total.multiply(coef, mc);
        }
        return total;
    }

    @Override
    public BigDecimal getVipPrice(SessionEntity sessionEntity) {
        return getBasePrice(sessionEntity).multiply(CoefficientType.VIP.getValue(),mc);
    }

    @Override
    public List<BigDecimal> getCoefs(MovieEntity movieEntity, LocalDate sessionDay, SeatEntity seatEntity) {
        List<BigDecimal> coefs = new ArrayList<>();
        coefs.add(getPremierCoef(movieEntity, sessionDay));
        coefs.add(getEndRentCoef(movieEntity, sessionDay));
        coefs.add(getBaseSeatCoef(seatEntity));
        coefs.add(getVipSeatCoef(seatEntity));
        return coefs;
    }

    public boolean isBetweenTwoDates(LocalDate start, LocalDate end, LocalDate sessionDay) {

        boolean result = false;

        if((sessionDay.isAfter(start)) && (sessionDay.isBefore(end))) {
            result = true;
        }
        return result;
    }

    public BigDecimal getPremierCoef(MovieEntity movieEntity, LocalDate sessionDay) {

        BigDecimal coef = CoefficientType.DEF.getValue();
        LocalDate startPremierTime = movieEntity.getStartRent();
        LocalDate endPremierTime = startPremierTime.plusWeeks(1);

        if(isBetweenTwoDates(startPremierTime, endPremierTime, sessionDay)){
            coef = CoefficientType.PREMIER.getValue();
        }
        return coef;
    }

    public BigDecimal getEndRentCoef(MovieEntity movieEntity, LocalDate sessionDay){

        BigDecimal coef = CoefficientType.DEF.getValue();
        LocalDate endRent = movieEntity.getEndRent();
        LocalDate fiveDays = endRent.minusDays(5);

        if(isBetweenTwoDates(fiveDays, endRent, sessionDay)){
            coef = CoefficientType.END.getValue();
        }
        return coef;
    }

    public BigDecimal getVipSeatCoef(SeatEntity seatEntity){

        BigDecimal coef = CoefficientType.DEF.getValue();
        if (seatEntity.getType().equalsIgnoreCase(SeatStatus.VIP.getStatus())){
            coef = CoefficientType.VIP.getValue();
        }
        return coef;
    }

    public BigDecimal getBaseSeatCoef(SeatEntity seatEntity){

        BigDecimal coef = CoefficientType.DEF.getValue();
        if (seatEntity.getType().equalsIgnoreCase(SeatStatus.BASE.getStatus())){
            coef = CoefficientType.BASE.getValue();
        }
        return coef;
    }

}

