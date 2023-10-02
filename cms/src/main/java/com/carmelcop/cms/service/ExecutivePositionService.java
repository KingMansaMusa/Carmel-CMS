package com.carmelcop.cms.service;

import com.carmelcop.cms.dto.ExecutivePositionDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ExecutivePositionService {
    List<ExecutivePositionDTO> getAllExecutivePositions();

    ResponseEntity getExecutivePositionById(UUID id);

    ResponseEntity getExecutivePositionsByActiveStatus(boolean active);

    ResponseEntity saveExecutivePosition(ExecutivePositionDTO executivePositionDTO);

    ResponseEntity updateExecutivePosition(UUID id, ExecutivePositionDTO executivePositionDTO);

    ResponseEntity updateExecutivePositionActiveStatus(UUID id, boolean active);
}
