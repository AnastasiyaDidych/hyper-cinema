package com.softserve.edu.hypercinema.controller;


import com.softserve.edu.hypercinema.converter.SessionConverter;
import com.softserve.edu.hypercinema.dto.SessionDto;
import com.softserve.edu.hypercinema.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.hypercinema.entity.SessionEntity;
import com.softserve.edu.hypercinema.service.SessionService;
import com.softserve.edu.hypercinema.util.SessionUtil;
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


    private SessionUtil sessionUtil;




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


    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<SessionDto> getSessions() {
        return sessionConverter.convertToDto(sessionService.getSessions());
    }


    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public void generateSession(@RequestBody SessionDto sessionDto) {

        sessionUtil.generateSession(sessionDto);
    }

    }


