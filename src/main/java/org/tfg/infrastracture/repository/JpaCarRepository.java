package org.tfg.infrastracture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCarRepository extends JpaRepository<CarEntity, String> {

}
