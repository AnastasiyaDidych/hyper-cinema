package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.dto.SeatDto;
import com.softserve.edu.hypercinema.entity.HallEntity;
import com.softserve.edu.hypercinema.entity.SeatEntity;
import com.softserve.edu.hypercinema.exception.SeatNotFoundException;
import com.softserve.edu.hypercinema.repository.SeatRepository;
import com.softserve.edu.hypercinema.service.SeatService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SeatServiceImpl implements SeatService {

    private static final String SEAT_NOT_FOUND_MESSAGE = "Could not find seat with id=";

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public void createSeat(SeatEntity seatEntity) {
        seatRepository.save(seatEntity);
    }

    @Override
    public List<SeatEntity> getSeats() {
        return seatRepository.findAll();
    }

    @Override

    public SeatEntity getSeat(Long id) {
        return seatRepository.findById(id).orElseThrow(() -> new SeatNotFoundException(SEAT_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public SeatEntity updateSeat(SeatEntity seatEntity) {
        System.out.println("1");
        SeatEntity seat = new SeatEntity();
        seat.setId(seatEntity.getId());
        seat.setHall(seatEntity.getHall());
        seat.setRow(seatEntity.getRow());
        seat.setNumber(seatEntity.getNumber());
        seat.setType(seatEntity.getType());
        return seatRepository.save(seat);

    }

    @Override
    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);
    }

}
