package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.ScheduleEntity;

import java.util.List;

public interface ScheduleService {

    ScheduleEntity getSchedule(Long id);


    void createSchedule(ScheduleEntity scheduleEntity);

    void deleteSchedule(Long id);

    void updateSchedule(ScheduleEntity scheduleEntity);

    List<ScheduleEntity> getAll();
}
