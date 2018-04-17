package com.softserve.edu.hypercinema.controller;


import com.softserve.edu.hypercinema.converter.HallConverter;
import com.softserve.edu.hypercinema.dto.HallDto;
import com.softserve.edu.hypercinema.exception.HallNotFoundException;
import com.softserve.edu.hypercinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/halls")
public class HallController {


    @Autowired
    private HallService hallService;

    @Autowired
    private HallConverter hallConverter;

    @PostMapping
    public void createHall(@RequestBody HallDto hallDto) {
        hallService.createHall(hallConverter.convertToEntity(hallDto));
    }

    @GetMapping("/{id}")
    public HallDto getHall(@PathVariable Long id) {
        return hallConverter.convertToDto(hallService.getHallById(id));
    }

    @PutMapping
    public void updateHall(@RequestBody HallDto hallDto) {
        if (hallService.getHallById(hallDto.getId()) != null) {
            hallService.updateHall(hallConverter.convertToEntity(hallDto));
        }else{
            throw new HallNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteHall(@PathVariable Long id) {
        hallService.deleteHall(id);
    }


}
