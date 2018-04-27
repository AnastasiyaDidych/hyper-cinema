package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.TicketConverter;
import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketConverter ticketConverter;

////    @PreAuthorize("hasRole('USER')")
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createTicket(@RequestBody TicketDto ticketDto){
//        ticketService.createTicket(ticketConverter.convertToEntity(ticketDto));
//    }

//    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void generateTicket(@RequestBody TicketDto ticket){
        ticketService.generateTicket(ticket);
    }

//    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}")
    public void updateTicket(@PathVariable Long id, @RequestBody TicketDto ticket){
        ticketService.updateTicket(id, ticket);
    }

//    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{id}")
    public TicketDto getTicket(@PathVariable Long id){
        return ticketConverter.convertToDto(ticketService.getTicket(id));
    }

//    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public List<TicketDto> getTickets(){
        return ticketConverter.convertToDto(ticketService.getTickets());
    }

//    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
    }

}
