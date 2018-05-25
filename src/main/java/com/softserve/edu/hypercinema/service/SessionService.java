package com.softserve.edu.hypercinema.service;


import com.softserve.edu.hypercinema.dto.Schedule;
import com.softserve.edu.hypercinema.dto.SessionDto;
import com.softserve.edu.hypercinema.entity.HallEntity;
import com.softserve.edu.hypercinema.entity.MovieEntity;
import com.softserve.edu.hypercinema.entity.SeatEntity;
import com.softserve.edu.hypercinema.entity.SessionEntity;
import org.springframework.boot.web.servlet.server.Session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SessionService {

    SessionEntity getSession(Long id);

    void createSession(SessionEntity sessionEntity);

    void deleteSession(Long id);

    void updateSession(SessionEntity sessionEntity);

    List<SessionEntity> getSessions();

    void generateSessionsForOneFilmForOneHallEnd(Long id);

    void copySessionsForOneWeek(String localDate);

    void generateSession(SessionDto sessionDto);

    boolean isOpen(LocalTime start, LocalTime end, LocalTime time);

    BigDecimal getBasePrice(SessionEntity sessionEntity);

    BigDecimal getVipPrice(SessionEntity sessionEntity);

    BigDecimal getVirtualPrice(SessionEntity sessionEntity);

    List<BigDecimal> getCoefs(MovieEntity movieEntity, LocalDate sessionDay, SeatEntity seatEntity);

    List<Schedule> schedule();

    List<SessionEntity> getAllByActive(boolean b);

}
