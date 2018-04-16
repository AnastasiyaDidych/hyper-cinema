package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.HallEntity;
import com.softserve.edu.hypercinema.exceptions.HallAlreadyExistException;
import com.softserve.edu.hypercinema.exceptions.HallNotFoundException;
import com.softserve.edu.hypercinema.repository.HallRepository;
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
        Optional<HallEntity> hallEntity1 = hallRepository.findById(hallEntity.getId());
        if(hallEntity1.isPresent()){
            throw new HallAlreadyExistException();
        }else{
            hallRepository.save(hallEntity);
        }
    }

    @Override
    public List<HallEntity> selectAllHalls() {
        List<HallEntity> hallList = new ArrayList<>();
        hallRepository.findAll().forEach(hallEntity -> hallList.add(hallEntity));
        return hallList;
    }

    @Override
    public HallEntity selectHallById(Long id) {
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

    @Override
    public void deleteHall(HallEntity hallEntity) {
        hallRepository.delete(hallEntity);
    }

}
