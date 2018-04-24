package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.TicketConverter;
import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketConverter ticketConverter;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public void createTicket(@RequestBody TicketDto ticketDto){
        ticketService.createTicket(ticketConverter.convertToEntity(ticketDto));
    }

//    @PreAuthorize("hasRole('USER')")
//    @PutMapping
//    public void updateTicket(@RequestBody TicketDto ticketDto){
//        ticketService.updateTicket(ticketConverter.convertToEntity(ticketDto));
//    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public void updateTicket(@PathVariable Long id, @RequestBody TicketDto ticketDto){
        ticketService.updateTicket(id, ticketConverter.convertToEntity(ticketDto));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public TicketDto getTicket(@PathVariable Long id){
        return ticketConverter.convertToDto(ticketService.getTicket(id));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<TicketDto> getTickets(){
        return ticketConverter.convertToDto(ticketService.getTickets());
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping
    public void deleteTicket(@RequestBody TicketDto ticketDto){
        ticketService.deleteTicket(ticketConverter.convertToEntity(ticketDto));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteTicketById(@PathVariable Long id){
        ticketService.deleteTicket(id);
    }
}
