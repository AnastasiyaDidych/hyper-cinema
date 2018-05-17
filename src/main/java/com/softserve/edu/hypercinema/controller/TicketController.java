package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.TicketConverter;
import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.dto.TicketFullDto;
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

//    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public void addTicket(@RequestBody TicketDto ticket) {
        ticketService.createTicket(ticketConverter.convertToEntity(ticket));
    }

//    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/{id}")
    public void updateTicket(@PathVariable Long id, @RequestBody TicketDto ticket){
        ticketService.updateTicket(id, ticketConverter.convertToEntity(ticket));
    }

    //    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public TicketDto getTicket(@PathVariable Long id/*, Authentication authentication*/) {
        return ticketConverter.convertToDto(ticketService.getTicket(id/*, authentication*/));
    }

//    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<TicketDto> getTickets(/*Authentication authentication*/) {
        return ticketConverter.convertToDto(ticketService.getTickets(/*authentication*/));
    }

//    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

//    ---------- Full DTO ----------

//    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/full/{id}")
    public void updateFullTicket(@PathVariable Long id, @RequestBody TicketFullDto fullTicket){
        ticketService.updateTicket(id, ticketConverter.convertFromFullDto(fullTicket));
    }

//    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/full")
    public List<TicketFullDto> getFullTickets(/*Authentication authentication*/) {
        return ticketConverter.convertToFullDto(ticketService.getTickets(/*authentication*/));
    }

//    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/full/{id}")
    public TicketFullDto getFullTicket(@PathVariable Long id/*, Authentication authentication*/){
        return ticketConverter.convertToFullDto(ticketService.getTicket(id/*, authentication*/));
    }

//    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/full/{id}")
    public void deleteFullTicket(@PathVariable Long id){
        deleteTicket(id);
    }

}

