package com.carmelcop.cms.controller;


import com.carmelcop.cms.dto.ExecutivePositionDTO;
import com.carmelcop.cms.model.ExecutivePosition;
import com.carmelcop.cms.service.ExecutivePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ExecutivePositionController {

    @Autowired
    ExecutivePositionService executivePositionService;


    @GetMapping("/get-executive-positions")
    public List<ExecutivePositionDTO> getAllExecutivePositions() {
        return executivePositionService.getAllExecutivePositions();
    }

    @GetMapping("/get-executive-position/{id}")
    public ResponseEntity getExecutivePositionById(@PathVariable(name = "id") UUID id) {
        return executivePositionService.getExecutivePositionById(id);
    }

    @GetMapping("/get-executive-positions-status")
    public ResponseEntity getExecutivePositionsByActiveStatus(@RequestParam(name = "active") boolean active) {
        return executivePositionService.getExecutivePositionsByActiveStatus(active);
    }

    @PostMapping("/save-executive-position")
    public ResponseEntity saveExecutivePosition(@RequestBody ExecutivePositionDTO executivePositionDTO) {
        return executivePositionService.saveExecutivePosition(executivePositionDTO);
    }

    @PutMapping("/edit-executive-position/{id}")
    public ResponseEntity updateExecutivePosition(@PathVariable(name = "id") UUID id,
                                                  @RequestBody ExecutivePositionDTO executivePositionDTO) {
        return executivePositionService.updateExecutivePosition(id, executivePositionDTO);
    }

    @PatchMapping("/edit-executive-position-active-status/{id}")
    public ResponseEntity updateExecutivePositionActiveStatus(@PathVariable(name = "id") UUID id,
                                                              @RequestParam(name = "active") boolean active) {
        return executivePositionService.updateExecutivePositionActiveStatus(id, active);
    }

}
