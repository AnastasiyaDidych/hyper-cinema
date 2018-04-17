package com.softserve.edu.hypercinema.repository;

import com.softserve.edu.hypercinema.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity,Long> {

}
