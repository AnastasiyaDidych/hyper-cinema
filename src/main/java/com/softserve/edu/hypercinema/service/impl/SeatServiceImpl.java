package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.SeatEntity;
import com.softserve.edu.hypercinema.exception.SeatAlredyExistException;
import com.softserve.edu.hypercinema.exception.SeatNotFoundException;
import com.softserve.edu.hypercinema.repository.SeatRepository;
import com.softserve.edu.hypercinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public void createSeat(SeatEntity seatEntity) {
        seatRepository.save(seatEntity);
    }

    @Override
    public List<SeatEntity> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public SeatEntity getSeatById(Long id) {
        return seatRepository.findById(id).orElseThrow(() -> new SeatNotFoundException());
    }

    @Override
    public void updateSeat(SeatEntity seatEntity) {
        seatRepository.save(seatEntity);
    }

    @Override
    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);

    }
}
