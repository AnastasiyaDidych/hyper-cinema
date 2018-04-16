package com.softserve.edu.hypercinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.softserve.edu.hypercinema.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmail(String email);

}
