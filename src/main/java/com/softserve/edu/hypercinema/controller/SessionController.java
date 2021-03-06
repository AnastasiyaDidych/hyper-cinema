package com.softserve.edu.hypercinema.controller;


import com.softserve.edu.hypercinema.converter.SessionConverter;
import com.softserve.edu.hypercinema.dto.Schedule;
import com.softserve.edu.hypercinema.dto.SessionDto;
import com.softserve.edu.hypercinema.entity.SessionEntity;
import com.softserve.edu.hypercinema.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionConverter sessionConverter;

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public void updateSession(@RequestBody SessionDto sessionDto) {
        sessionService.updateSession(sessionConverter.convertToEntity(sessionDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public void deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public SessionDto getSession(@PathVariable Long id) {
        return sessionConverter.convertToDto(sessionService.getSession(id));
    }


    @GetMapping("/allsessions")
    @PreAuthorize("hasRole('USER')")
    public List<SessionDto> getSessions() {
        return sessionConverter.convertToDto(sessionService.getSessions());
    }


    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public void generateSession(@RequestBody SessionDto sessionDto) {
        sessionService.generateSession(sessionDto);
    }

    @PostMapping("/generateday/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public void generateForDay(@PathVariable Long id) {
        sessionService.generateSessionsForOneFilmForOneHallEnd(id);
    }

    @PostMapping("/generateweek")
    @PreAuthorize("hasRole('MANAGER')")
    public void generateForWeek(@RequestParam String date) {
        sessionService.copySessionsForOneWeek(date);
    }

    @GetMapping("/schedule")
    @PreAuthorize("hasRole('USER')")
    public List<Schedule> getSchedules() {
        return sessionService.schedule();
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<SessionDto> getActiveSessions(){
        return sessionConverter.convertToDto(sessionService.getAllByActive(true));
    }

}

