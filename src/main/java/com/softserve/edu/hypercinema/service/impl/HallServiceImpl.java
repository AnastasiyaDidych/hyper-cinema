package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.HallEntity;
import com.softserve.edu.hypercinema.exception.ConflictException;
import com.softserve.edu.hypercinema.exception.HallNotFoundException;
import com.softserve.edu.hypercinema.repository.HallRepository;
import com.softserve.edu.hypercinema.service.HallService;
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
    SeatServiceImpl seatService;


    @Override
    public void createHall(HallEntity hallEntity) {
        try {
            hallRepository.saveAndFlush(hallEntity);
            fillHall(hallEntity);
        } catch (Exception e) {
            throw new ConflictException();
        }
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
        if (hallEntity.getId() != null) {
            hallRepository.save(hallEntity);
        } else {
            throw new HallNotFoundException();
        }

//        try {
//            hallRepository.save(hallEntity);
//        } catch (Exception e) {
//            throw new HallNotFoundException();
//        }
    }

    @Override
    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }


    private void fillHall(HallEntity hallEntity) {

        String type = hallEntity.getType();

        switch (type) {
            case "static":
                fillStaticHall(hallEntity);
                break;
            case "virtual":
                fillStaticHall(hallEntity);
                break;
            case "personal":
                fillPersonalHall(hallEntity);
                break;
            default:
                //logic
                break;
        }
    }


    /**
     * param i stands for row number;
     * param j stands for seat number in range [1-10];
     */
    private void fillStaticHall(HallEntity hallEntity) {
        try {
            int capacity = hallEntity.getCapacity();
            int rows = (capacity / 10) + 1;
            int k = capacity - ((rows - 1) * 10);

            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= (i == rows ? k : 10); j++) {
                    if (i == k) {
                        seatService.createSeat(SeatServiceImpl.seatData(j, i, hallEntity, "VIP"));
                    } else {
                        seatService.createSeat(SeatServiceImpl.seatData(j, i, hallEntity, null));
                    }
                }
            }
        } catch (Exception e) {
            throw new HallNotFoundException();
        }
    }


    private void fillPersonalHall(HallEntity hallEntity) {
        seatService.createSeat(SeatServiceImpl.seatData(1, 1, hallEntity, "personal"));
    }


}
