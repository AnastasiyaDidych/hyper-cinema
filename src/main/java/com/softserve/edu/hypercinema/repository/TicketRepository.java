package com.softserve.edu.hypercinema.repository;

import com.softserve.edu.hypercinema.entity.SeatEntity;
import com.softserve.edu.hypercinema.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

//    Optional<TicketRepository> findBySeat(SeatEntity seat);

}
