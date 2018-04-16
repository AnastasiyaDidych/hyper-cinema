package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.SeatEntity;
import com.softserve.edu.hypercinema.exceptions.SeatAlredyExistException;
import com.softserve.edu.hypercinema.exceptions.SeatNotFoundException;
import com.softserve.edu.hypercinema.repository.SeatRepository;
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
        Optional<SeatEntity> seatEntity1 = seatRepository.findById(seatEntity.getId());
        if (seatEntity1.isPresent()) {
            throw new SeatAlredyExistException();
        } else {
            seatRepository.save(seatEntity);
        }

    }

    @Override
    public List<SeatEntity> selectAllSeats() {
        List<SeatEntity> seatList = new ArrayList<>();
        seatRepository.findAll().forEach(seatEntity -> seatList.add(seatEntity));
        return seatList;
    }

    @Override
    public SeatEntity selectSeatById(Long id) {
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

    @Override
    public void deleteSeat(SeatEntity seatEntity) {
        seatRepository.delete(seatEntity);
    }

}
