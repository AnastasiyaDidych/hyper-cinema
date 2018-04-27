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
    public void updateHall(Long id, HallEntity hallEntity) {
        hallEntity.setId(id);
        hallRepository.save(hallEntity);
    }


    @Override
    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }


    private void fillHall(HallEntity hallEntity) {
        if (hallEntity.getType().equalsIgnoreCase(HallTypes.STATIC.toString())) {
            fillStaticHall(hallEntity);
        } else {
            //logic
        }
    }


    private void fillStaticHall(HallEntity hallEntity/*, int row_capacity*/) {

        final int row_capacity = 10;
        int capacity = hallEntity.getCapacity();

        int rows = (capacity / row_capacity) + (capacity % 10 == 0 ? 0 : 1);
        int k = capacity - ((rows - 1) * row_capacity);

        seatService.createSeat(SeatEntity.builder()
                .number(1)
                .row(0)
                .hall(hallEntity)
                .type("virtual")
                .build());

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= (i == rows ? k : row_capacity); j++) {
                seatService.createSeat(SeatEntity.builder()
                        .number(j)
                        .row(i)
                        .hall(hallEntity)
                        .type(i == rows ? "VIP" : "base")
                        .build());
            }

        }
    }

}
