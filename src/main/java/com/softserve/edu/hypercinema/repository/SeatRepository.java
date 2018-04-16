package com.softserve.edu.hypercinema.repository;

import com.softserve.edu.hypercinema.entity.SeatEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<SeatEntity, Integer>{
}
