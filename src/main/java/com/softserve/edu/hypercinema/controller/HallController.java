package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.HallConverter;
import com.softserve.edu.hypercinema.dto.HallDto;
import com.softserve.edu.hypercinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/halls")
public class HallController {

    @Autowired
    private HallService hallService;

    @Autowired
    private HallConverter hallConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createHall(@RequestBody HallDto hallDto) {
        hallService.createHall(hallConverter.convertToEntity(hallDto));
    }

    @GetMapping("/{id}")
    public HallDto getHall(@PathVariable Long id) {
        return hallConverter.convertToDto(hallService.getHall(id));
    }

    @PutMapping
    public void updateHall(@RequestBody HallDto hallDto) {
        hallService.updateHall(hallConverter.convertToEntity(hallDto));
    }

    @DeleteMapping("/{id}")
    public void deleteHall(@PathVariable Long id) {
        hallService.deleteHall(id);
    }

}
