package com.carmelcop.cms.service;

import com.carmelcop.cms.dto.PositionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface SetupsService {

    public ResponseEntity savePosition(PositionDTO positionDTO);

    List<PositionDTO> getAllPositions();

    ResponseEntity updatePosition(UUID id, PositionDTO positionDTO);

    ResponseEntity getPositionById(UUID id);
}
