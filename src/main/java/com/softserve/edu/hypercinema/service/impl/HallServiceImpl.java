package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.constants.HallTypes;
import com.softserve.edu.hypercinema.entity.HallEntity;
import com.softserve.edu.hypercinema.entity.SeatEntity;
import com.softserve.edu.hypercinema.exception.HallNotFoundException;
import com.softserve.edu.hypercinema.exception.InvalidDataException;
import com.softserve.edu.hypercinema.repository.HallRepository;
import com.softserve.edu.hypercinema.service.HallService;
import com.softserve.edu.hypercinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class HallServiceImpl implements HallService {


    private static final String HALL_NOT_FOUND_MESSAGE = "Could not find hall with id=";

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private SeatService seatService;

    @Override
    public void createHall(HallEntity hallEntity) {
        if (hallEntity.getCapacity() < 0) {
            throw new InvalidDataException("Invalid value for capacity. Only positive values");
        }
        hallRepository.saveAndFlush(hallEntity);
        fillHall(hallEntity);
    }


    @Override
    public List<HallEntity> getHalls() {
        return hallRepository.findAll();
    }


    @Override
    public HallEntity getHall(Long id) {
        return hallRepository.findById(id).orElseThrow(() -> new HallNotFoundException(HALL_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public void updateHall(HallEntity hallEntity) {
        getHall(hallEntity.getId());
        hallRepository.save(hallEntity);
    }


    @Override
    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }


    private void fillHall(HallEntity hallEntity) {

        String type = hallEntity.getType().toUpperCase();

        switch (HallTypes.valueOf(type)) {
            case STATIC:
                fillStaticHall(hallEntity);
                break;
            case VIRTUAL:
                //TODO
                break;
            case PERSONAL:
                fillPersonalHall(hallEntity);
                break;
            default:
                //TODO
                //logic
                break;
        }
    }


    private void fillStaticHall(HallEntity hallEntity) {

        int capacity = hallEntity.getCapacity();

        //fails when I set 30, 40, 100 capacity
        //because than there are 4, 5 and 11 rows
        int rows = (capacity / 10) + 1;
        int k = capacity - ((rows - 1) * 10);
        final int row_capacity = 10;
        String type = hallEntity.getType();
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= (i == rows ? k : row_capacity); j++) {
                seatService.createSeat(SeatEntity.builder()
                        .number(j)
                        .row(i)
                        .hall(hallEntity)
                        .status(i == rows ? "VIP" : "base")
                        .build());
            }

        }
    }


    private void fillPersonalHall(HallEntity hallEntity) {
        seatService.createSeat(SeatEntity.builder()
                .number(1)
                .row(1)
                .hall(hallEntity)
                .status("personal")
                .build());
    }
}
