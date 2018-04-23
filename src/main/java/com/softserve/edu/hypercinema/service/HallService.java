package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.HallEntity;

import java.util.List;

public interface HallService {

    HallEntity createHall(HallEntity hallEntity);

    List<HallEntity> getHalls();

    HallEntity getHall(Long id);

    HallEntity updateHall(HallEntity hallEntity);

    void deleteHall(Long id);

}