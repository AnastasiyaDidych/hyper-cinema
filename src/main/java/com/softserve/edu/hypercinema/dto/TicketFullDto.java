package com.softserve.edu.hypercinema.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TicketFullDto extends BaseDto {

    private long id;

    private String filmName;

    private String tech;

    private LocalDate sessionDate;

    private LocalTime sessionTime;

    private int seatRow;

    private int seatNumber;

    private String hallName;

    private BigDecimal price;

    private String barcode;

    private boolean virtualActive;

}
