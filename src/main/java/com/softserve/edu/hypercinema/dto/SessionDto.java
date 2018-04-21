package com.softserve.edu.hypercinema.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class SessionDto extends BaseDto {

    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean active;



}
