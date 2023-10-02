package com.carmelcop.cms.repository;

import com.carmelcop.cms.model.ExecutivePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExecutivePositionRepository extends JpaRepository<ExecutivePosition, UUID> {

    List<ExecutivePosition> findAllByActiveEquals(boolean active);

}
