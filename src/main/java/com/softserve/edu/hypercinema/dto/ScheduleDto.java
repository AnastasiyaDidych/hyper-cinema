package com.softserve.edu.hypercinema.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ScheduleDto extends BaseDto {
    Long id;
    LocalTime localTime;
}
