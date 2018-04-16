package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.SeatEntity;
import com.softserve.edu.hypercinema.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SeatServiceImpl implements SeatService{

    @Autowired
    private SeatRepository seatRepository;
    @Override
    public List<SeatEntity> selectAllSeats() {
        return null;
    }

    @Override
    public SeatEntity selectSeatById(Long id) {
        SeatEntity seatEntity = new SeatEntity();
        return seatEntity;
    }

    @Override
    public void updateSeat(SeatEntity seatEntity) {

    }


    @Override
    public void deleteSeatById(Long id) {

    }
}
