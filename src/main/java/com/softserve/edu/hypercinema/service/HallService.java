package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.HallEntity;

import java.util.List;

public interface HallService {

    List<HallEntity> selectAllHalls();

    HallEntity selectHallById(Long id);

    void updateHall(HallEntity hallEntity);

    void deleteHallById(Long id);

}