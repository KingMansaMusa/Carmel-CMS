package com.carmelcop.cms.repository;

import com.carmelcop.cms.model.Executive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExecutiveRepository extends JpaRepository<Executive, UUID> {

    List<Executive> findAllByGroupIdEquals(UUID groupId);

    List<Executive> findAllByMemberIdEquals(UUID memberId);

    List<Executive> findAllByPositionIdEquals(UUID positionId);

}
