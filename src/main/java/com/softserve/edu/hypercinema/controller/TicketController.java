package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.TicketConverter;
import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketConverter ticketConverter;

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}")
    public void updateTicket(@PathVariable Long id, @RequestBody TicketDto ticket){
        ticketService.updateTicket(id, ticketConverter.convertToEntity(ticket));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public TicketDto getTicket(@PathVariable Long id, Authentication authentication){
        return ticketConverter.convertToDto(ticketService.getTicket(id, authentication));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<TicketDto> getTickets(Authentication authentication){
        return ticketConverter.convertToDto(ticketService.getTickets(authentication));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
    }

}
