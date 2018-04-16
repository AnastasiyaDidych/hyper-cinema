package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.SeatEntity;

import java.util.List;

public interface SeatService {

    List<SeatEntity> selectAllSeats();

    SeatEntity selectSeatById(Long id);

    void updateSeat(SeatEntity seatEntity);

    void deleteSeatById(Long id);

}
