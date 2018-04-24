package com.softserve.edu.hypercinema.controller;


import com.softserve.edu.hypercinema.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;



}

