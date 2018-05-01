package com.softserve.edu.hypercinema.dto;

import com.softserve.edu.hypercinema.entity.HallEntity;
import com.softserve.edu.hypercinema.entity.MovieEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SessionDto extends BaseDto {


    private Long movieId;
    private Long hallId;
    private String date;
    private String startTime;
    private boolean virtualActive;




}
