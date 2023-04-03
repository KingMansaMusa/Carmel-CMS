package com.carmelcop.cms.service;

import com.carmelcop.cms.dto.CellGroupDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CellGroupService {
    List<CellGroupDTO> getAllCellGroups();

    ResponseEntity findCellGroupById(UUID id);

    List<CellGroupDTO> getActiveCellGroups(boolean active);

    ResponseEntity saveCellGroup(CellGroupDTO cellGroupDTO);

    ResponseEntity updateCellGroup(UUID id, CellGroupDTO cellGroupDTO);

    ResponseEntity updateCellGroupActiveStatus(UUID id, boolean active);
}
