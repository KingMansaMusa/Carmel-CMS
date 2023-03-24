package com.carmelcop.cms.repository;

import com.carmelcop.cms.model.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, UUID> {
    List<MaritalStatus> findAllByActiveTrue();
}
