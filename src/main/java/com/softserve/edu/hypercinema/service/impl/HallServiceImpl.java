package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.HallEntity;
import com.softserve.edu.hypercinema.entity.SeatEntity;
import com.softserve.edu.hypercinema.exception.ConflictException;
import com.softserve.edu.hypercinema.exception.HallNotFoundException;
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
    SeatService seatService;

    @Override
    public void createHall(HallEntity hallEntity) {
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
        if (hallEntity.getId() != null) {
            hallRepository.save(hallEntity);
        } else {
            throw new HallNotFoundException();
        }
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
//                HallEntity hallEntity1 = new HallEntity();
//                hallEntity1.setCapacity(hallEntity.getCapacity());
//                hallEntity1.setType("virtual");
//                hallEntity1.setName(hallEntity.getName() + "VR");
//                createHall(hallEntity1);
//                fillStaticHall(hallEntity1);
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
        if (hallEntity != null) {
            int capacity = hallEntity.getCapacity();
            int rows = (capacity / 10) + 1;
            int k = capacity - ((rows - 1) * 10);
            String type = hallEntity.getType();
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= (i == rows ? k : 10); j++) {
                    seatService.createSeat(SeatEntity.builder()
                            .number(j)
                            .row(i)
                            .hall(hallEntity)
//                            .status(i == rows ? "VIP" : null).build());
                            .status(type == "static" ? (i == rows ? "VIP" : null) : null).build());


                }

            }
        } else {
            throw new HallNotFoundException();
        }
    }


    private void fillPersonalHall(HallEntity hallEntity) {
        if (hallEntity != null) {
            SeatEntity.builder().number(1).row(1).hall(hallEntity).status("Personal");
        } else {
            throw new HallNotFoundException();
        }
    }
}