package com.softserve.edu.hypercinema.controller;


import com.softserve.edu.hypercinema.converter.HallConverter;
import com.softserve.edu.hypercinema.dto.HallDto;
import com.softserve.edu.hypercinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/halls")

public class HallController {

    @Autowired
    private HallService hallService;

    @Autowired
    private HallConverter hallConverter;

    @PostMapping
//    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createHall(@RequestBody HallDto hallDto) {
        hallService.createHall(hallConverter.convertToEntity(hallDto));
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('USER')")
    public HallDto getHall(@PathVariable Long id) {
        return hallConverter.convertToDto(hallService.getHall(id));
    }


    @GetMapping
    public List<HallDto> getHalls(){
        return hallConverter.convertToDto(hallService.getHalls());
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('MANAGER')")
    public void deleteHall(@PathVariable Long id) {
        hallService.deleteHall(id);
    }

}
