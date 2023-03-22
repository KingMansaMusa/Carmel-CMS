package com.carmelcop.cms.repository;

import com.carmelcop.cms.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GenderRepository extends JpaRepository<Gender, UUID> {

    List<Gender> findAllByActiveTrue();
}
