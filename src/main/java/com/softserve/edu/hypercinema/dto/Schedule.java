package com.softserve.edu.hypercinema.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Schedule {
    String title;
    LocalDate localDate;
    List<ScheduleDto> sessionEntityList;
}
