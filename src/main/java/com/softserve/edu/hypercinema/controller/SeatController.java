package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.SeatConverter;
import com.softserve.edu.hypercinema.dto.SeatDto;
import com.softserve.edu.hypercinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private SeatConverter seatConverter;

    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('USER')")
    public SeatDto getSeat(@PathVariable("id") Long id) {
        return seatConverter.convertToDto(seatService.getSeat(id));
    }

    @PutMapping("/{id}")
    public void updateSeat(@RequestBody SeatDto seatDto) {
        System.out.println("2");
        seatConverter.convertToDto(seatService.updateSeat(seatConverter.convertToEntity(seatDto)));
    }
}
