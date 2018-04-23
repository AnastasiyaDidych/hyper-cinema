package com.softserve.edu.hypercinema.dto;

import com.softserve.edu.hypercinema.entity.DayTimeEntity;
import com.softserve.edu.hypercinema.model.DaysModel;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ScheduleDto extends BaseDto {

    private DayTimeEntity dayTime;

}
