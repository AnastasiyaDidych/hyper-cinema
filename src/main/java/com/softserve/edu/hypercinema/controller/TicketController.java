package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.TicketConverter;
import com.softserve.edu.hypercinema.dto.TicketDto;
import com.softserve.edu.hypercinema.dto.TicketFullDto;
import com.softserve.edu.hypercinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public void addTicket(@RequestBody TicketFullDto ticket) {
        ticketService.createTicket(ticketConverter.convertToEntity(ticket));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}")
    public void updateTicket(@PathVariable Long id, @RequestBody TicketFullDto ticket){
        ticketService.updateTicket(id, ticketConverter.convertToEntity(ticket));
    }

    @GetMapping("/{sessionId}")
    public List<TicketFullDto> getUnavailableTickets(@PathVariable Long sessionId) {
        return ticketConverter.convertToDto(ticketService.getUnavailableTickets(sessionId));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

//    ---------- Full DTO ----------

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/full")
    public List<TicketFullDto> getAllFullTickets(Authentication authentication) {
        return ticketConverter.convertToFullDto(ticketService.getTickets(authentication));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/my")
    public List<TicketFullDto> getMyFullTickets(Authentication authentication) {
        return ticketConverter.convertToFullDto(ticketService.getMyTickets(authentication));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/full/{id}")
    public TicketFullDto getFullTicket(@PathVariable Long id, Authentication authentication){
        return ticketConverter.convertToDto(ticketService.getTicket(id, authentication));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/send/{id}")
    public void sendTicketByMail(@PathVariable Long id, Authentication authentication){
        ticketService.sendMessage(ticketService.getTicket(id, authentication));
    }

    @GetMapping
    public Page<TicketFullDto> getPage(Pageable pageable){
        return ticketConverter.covertPageToFullDto(ticketService.getTicketsByPage(pageable));
    }

}

