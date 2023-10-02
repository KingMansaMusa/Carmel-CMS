package com.carmelcop.cms.service;

import com.carmelcop.cms.dto.ExecutiveDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ExecutiveService {
    List<ExecutiveDTO> getAllExecutives();

    ResponseEntity getExecutiveById(UUID id);

    ResponseEntity getExecutivesByGroupId(UUID groupId);

    ResponseEntity getExecutivesByMemberId(UUID memberId);

    ResponseEntity getExecutivesByPositionId(UUID positionId);

    ResponseEntity updateExecutive(UUID id, ExecutiveDTO executiveDTO);
}
