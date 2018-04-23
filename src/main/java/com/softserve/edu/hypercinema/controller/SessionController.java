package com.softserve.edu.hypercinema.controller;


import com.softserve.edu.hypercinema.converter.SessionConverter;
import com.softserve.edu.hypercinema.dto.SessionDto;
import com.softserve.edu.hypercinema.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionConverter sessionConverter;

    @PostMapping("/create")
    public void createSession(@RequestBody SessionDto sessionDto) {
        sessionService.createSession(sessionConverter.convertToEntity(sessionDto));
    }

    @PutMapping("/{id}")
    public void updateSession(@RequestBody SessionDto sessionDto) {
        sessionService.updateSession(sessionConverter.convertToEntity(sessionDto));
    }

    @DeleteMapping("/{id}")
    public void deleteSession(@RequestBody Long id) {
        sessionService.deleteSession(id);
    }

    @GetMapping("/{id}")
    public SessionDto getSession(@RequestBody Long id) {
        return sessionConverter.convertToDto(sessionService.getSession(id));
    }

    @GetMapping("/all")
    public List<SessionDto> getSessions() {
        return sessionConverter.convertToDto(sessionService.getAll());
    }
}

