package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.HallEntity;
import com.softserve.edu.hypercinema.exceptions.HallNotFoundException;
import com.softserve.edu.hypercinema.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HallServiceImpl implements HallService {

    @Autowired
    private HallRepository hallRepository;

    @Override
    public List<HallEntity> selectAllHalls() {
        List<HallEntity> hallList = new ArrayList<>();
        hallRepository.findAll().forEach(hallEntity -> hallList.add(hallEntity));
        return hallList;
    }

    @Override
    public HallEntity selectHallById(Long id) {
        HallEntity hallEntity = new HallEntity();
        hallEntity = hallRepository.findById(id).orElseThrow(() -> new HallNotFoundException());
        return hallEntity;
    }

    @Override
    public void updateHall(HallEntity hallEntity) {
        hallRepository.save(hallEntity);
    }

    @Override
    public void deleteHallById(Long id) {
        hallRepository.deleteById(id);

    }
}
