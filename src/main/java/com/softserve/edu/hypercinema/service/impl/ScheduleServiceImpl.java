package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.ScheduleEntity;
import com.softserve.edu.hypercinema.repository.ScheduleRepository;
import com.softserve.edu.hypercinema.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService
{
    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public ScheduleEntity getSchedule(Long id) {
        return scheduleRepository.getOne(id);
    }

    @Override
    public void createSchedule(ScheduleEntity scheduleEntity) {

        scheduleRepository.save(scheduleEntity);
    }

    @Override
    public void deleteSchedule(Long id) {
            scheduleRepository.deleteById(id);
    }

    @Override
    public void updateSchedule(ScheduleEntity scheduleEntity) {
        scheduleRepository.save(scheduleEntity);
    }

    @Override
    public List<ScheduleEntity> getSchedules() {
        return scheduleRepository.findAll();
    }
}
