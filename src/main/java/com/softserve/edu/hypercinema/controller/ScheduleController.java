package com.softserve.edu.hypercinema.controller;


import com.softserve.edu.hypercinema.converter.impl.ScheduleConverter;
import com.softserve.edu.hypercinema.dto.ScheduleDto;
import com.softserve.edu.hypercinema.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleConverter scheduleConverter;

    @PostMapping
    public void createSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.createSchedule(scheduleConverter.convertToEntity(scheduleDto));

    }

    @PutMapping
    public  void updateSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.updateSchedule(scheduleConverter.convertToEntity(scheduleDto));
    }

    @DeleteMapping("/{id}")
    public  void deleteSchedule(@PathVariable Long id){
        scheduleService.deleteSchedule(id);
    }


    @GetMapping("/{id}")
    public ScheduleDto getSchedule(@PathVariable Long id){
        return scheduleConverter.convertToDto(scheduleService.getSchedule(id));
    }

    @GetMapping
    public List<ScheduleDto> getAllSchedules(){
        return  scheduleConverter.convertToDto(scheduleService.getSchedules());
    }
}
