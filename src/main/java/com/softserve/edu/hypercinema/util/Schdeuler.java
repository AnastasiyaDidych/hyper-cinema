package com.softserve.edu.hypercinema.util;

import com.softserve.edu.hypercinema.entity.SessionEntity;
import com.softserve.edu.hypercinema.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Schdeuler {
    @Autowired
    SessionRepository sessionRepository;

    @Scheduled(fixedRate = 60000)
    public void changeActive() {
        List<SessionEntity> sessionEntities = sessionRepository.findAllByActiveAndDate(true, LocalDate.now());
        for (int i = 0; i < sessionEntities.size(); i++) {
            if (sessionEntities.get(i).getStartTime().isBefore(LocalTime.now())) {
                sessionEntities.get(i).setActive(false);
            }
            sessionRepository.save(sessionEntities.get(i));


        }
    }

    @Scheduled(fixedRate = 86400000)
    public void changeActiveByDay() {
        List<SessionEntity> sessionEntities = sessionRepository.findAll();
        for (int i = 0; i < sessionEntities.size(); i++) {
            if (sessionEntities.get(i).getDate().isBefore(LocalDate.now())) {
                sessionEntities.get(i).setActive(false);
            }
            sessionRepository.save(sessionEntities.get(i));
        }
    }


}
