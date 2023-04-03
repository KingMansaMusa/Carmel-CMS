package com.carmelcop.cms.repository;

import com.carmelcop.cms.model.CellGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CellGroupRepository extends JpaRepository<CellGroup, UUID> {

    List<CellGroup> findCellGroupByActiveEquals(boolean active);

}
