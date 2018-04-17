package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.HallEntity;
import com.softserve.edu.hypercinema.exception.HallAlreadyExistException;
import com.softserve.edu.hypercinema.exception.HallNotFoundException;
import com.softserve.edu.hypercinema.repository.HallRepository;
import com.softserve.edu.hypercinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public void createHall(HallEntity hallEntity) {
            hallRepository.save(hallEntity);
    }

    @Override
    public List<HallEntity> getAllHalls() {
        return hallRepository.findAll();
    }

    @Override
    public HallEntity getHallById(Long id) {
        return hallRepository.findById(id).orElseThrow(() -> new HallNotFoundException());
    }

    @Override
    public void updateHall(HallEntity hallEntity) {
        hallRepository.save(hallEntity);
    }

    @Override
    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }

}
