package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.TicketConverter;
import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by VR
 * 23:19, 16.04.2018
 */

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketConverter ticketConverter;

    @PostMapping
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
    public List<TicketDto> getAllTickets(){
        return ticketConverter.convertToDto(ticketService.selectAllTickets());
    }

    @DeleteMapping
    public void deleteTicket(@RequestBody TicketDto ticketDto){
        ticketService.deleteTicket(ticketConverter.convertToEntity(ticketDto));
    }

}
