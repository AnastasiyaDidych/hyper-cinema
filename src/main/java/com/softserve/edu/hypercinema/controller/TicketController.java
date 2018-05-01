package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.TicketConverter;
import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketConverter ticketConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTicket(@RequestBody TicketDto ticketDto){
        ticketService.createTicket(ticketConverter.convertToEntity(ticketDto));
    }

    @PutMapping
    public void updateTicket(@RequestBody TicketDto ticketDto){
        ticketService.updateTicket(ticketConverter.convertToEntity(ticketDto));
    }

    @GetMapping("/{id}")
    public TicketDto getTicket(@PathVariable Long id){
        return ticketConverter.convertToDto(ticketService.getTicket(id));
    }

    @GetMapping
    public List<TicketDto> getTickets(){
        return ticketConverter.convertToDto(ticketService.getTickets());
    }

    @DeleteMapping
    public void deleteTicket(@RequestBody TicketDto ticketDto){
        ticketService.deleteTicket(ticketConverter.convertToEntity(ticketDto));
    }

}
