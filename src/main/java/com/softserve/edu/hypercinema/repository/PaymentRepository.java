package com.softserve.edu.hypercinema.repository;

import com.softserve.edu.hypercinema.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
