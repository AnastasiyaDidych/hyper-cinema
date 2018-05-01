package com.softserve.edu.hypercinema.util;

import com.softserve.edu.hypercinema.dto.SessionDto;
import com.softserve.edu.hypercinema.entity.MovieEntity;
import com.softserve.edu.hypercinema.entity.SessionEntity;
import com.softserve.edu.hypercinema.service.HallService;
import com.softserve.edu.hypercinema.service.MovieService;
import com.softserve.edu.hypercinema.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SessionUtil {
    final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter TIME_FORMATER = DateTimeFormatter.ofPattern("HH mm");
    private final String MOVIE_ALREADY_EXISTS_MESSAGE = "QWE";

    @Autowired
    private SessionService sessionService;
    @Autowired
    private MovieService movieService;

    @Autowired
    private  HallService hallService;

    public void generateSession(SessionDto sessionDto) {

        SessionEntity sessionEntity = new SessionEntity();
        MovieEntity movieEntity = movieService.getMovie(sessionDto.getMovieId());
        sessionEntity.setMovie(movieEntity);
        sessionEntity.setHall(hallService.getHall(sessionDto.getHallId()));
        sessionEntity.setDate(LocalDate.parse(sessionDto.getDate(), DATE_FORMAT));
        LocalTime startTime = LocalTime.parse(sessionDto.getStartTime(), TIME_FORMATER);
        sessionEntity.setStartTime(startTime);
        sessionEntity.setEndTime(startTime.plusMinutes(movieEntity.getDuration() + 15));
        sessionEntity.setVirtualActive(sessionDto.isVirtualActive());
        //generateTicketsForSession(sessionEntity);
        sessionService.createSession(sessionEntity);


    }
}
