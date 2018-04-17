package com.softserve.edu.hypercinema.repository;

import com.softserve.edu.hypercinema.entity.HallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<HallEntity, Long> {

}
