package com.softserve.edu.hypercinema.controller;


import com.softserve.edu.hypercinema.converter.SessionConverter;
import com.softserve.edu.hypercinema.dto.SessionDto;
import com.softserve.edu.hypercinema.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionConverter sessionConverter;

    @GetMapping("/{id}")
    public SessionDto getSession(@PathVariable Long id) {
        return sessionConverter.convertToDto(sessionService.getSession(id));
    }

    public void createSession() {

    }
}

