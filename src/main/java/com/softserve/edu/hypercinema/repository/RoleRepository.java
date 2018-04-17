package com.softserve.edu.hypercinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.softserve.edu.hypercinema.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

	Optional<RoleEntity> findByName(String name);

}
