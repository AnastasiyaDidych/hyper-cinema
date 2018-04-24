package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.impl.SeatConverter;
import com.softserve.edu.hypercinema.dto.SeatDto;
import com.softserve.edu.hypercinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private SeatConverter seatConverter;

    @GetMapping("/{id}")
    public SeatDto getSeat(@PathVariable("id") Long id) {
        return seatConverter.convertToDto(seatService.getSeat(id));
    }

}
