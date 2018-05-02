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
public class SessionServiceImpl  implements SessionService {

    private final MathContext mc = new MathContext(3);
    final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter TIME_FORMATER=DateTimeFormatter.ofPattern("HH mm");

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

