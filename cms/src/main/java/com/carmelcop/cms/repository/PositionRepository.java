package com.carmelcop.cms.repository;

import com.carmelcop.cms.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PositionRepository extends JpaRepository<Position, UUID> {


}
