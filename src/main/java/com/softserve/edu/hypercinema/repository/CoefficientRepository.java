package com.softserve.edu.hypercinema.repository;

import com.softserve.edu.hypercinema.entity.CoefficientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoefficientRepository extends JpaRepository<CoefficientEntity, Long> {
}
