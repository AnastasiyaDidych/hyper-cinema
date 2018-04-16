package com.softserve.edu.hypercinema.repository;

import com.softserve.edu.hypercinema.entity.HallEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends CrudRepository<HallEntity, Integer> {

}
