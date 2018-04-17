package com.softserve.edu.hypercinema.controller;


import com.softserve.edu.hypercinema.converter.ScheduleConverter;
import com.softserve.edu.hypercinema.dto.ScheduleDto;
import com.softserve.edu.hypercinema.entity.ScheduleEntity;
import com.softserve.edu.hypercinema.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

import static com.softserve.edu.hypercinema.model.DaysModel.Monday;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleConverter scheduleConverter;

    @GetMapping("/generate/random")
    public String generateSchedule() {
    	for(int i =0;i<5;i++) {
			ScheduleEntity scheduleEntity = new ScheduleEntity();
			scheduleEntity.setDaysModel(Monday);
			scheduleEntity.setTime(LocalTime.now());
			scheduleService.createSchedule(scheduleEntity);

		}
		return "redirect:/";
	}




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
        return  scheduleConverter.convertToDto(scheduleService.getAll());
    }


}
