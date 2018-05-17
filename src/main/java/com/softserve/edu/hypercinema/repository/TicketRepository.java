package com.softserve.edu.hypercinema.repository;

import com.softserve.edu.hypercinema.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    @Query("SELECT t FROM TicketEntity t WHERE t.session.id = :sessionId AND t.seat.id = :seatId")
    List<TicketEntity> findAllTicketBySessionIdAndSeatId(
            @Param("sessionId") Long sessionId,
            @Param("seatId") Long seatId);

}
