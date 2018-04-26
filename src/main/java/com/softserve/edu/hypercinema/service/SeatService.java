package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.HallEntity;
import com.softserve.edu.hypercinema.entity.SeatEntity;

import java.util.List;

public interface SeatService {

    void createSeat(SeatEntity seatEntity);

    List<SeatEntity> getSeats();

    SeatEntity getSeat(Long id);

    void updateSeat(SeatEntity seatEntity);

    void deleteSeat(Long id);

//    SeatEntity seatData(int number, int row, HallEntity hallEntity, String status);

}
