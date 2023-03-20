package com.carmelcop.cms.controller;

import com.carmelcop.cms.dto.PositionDTO;
import com.carmelcop.cms.service.SetupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class SetupsController {

    @Autowired
    SetupsService setupsService;

    @GetMapping("/get-positions")
    public List<PositionDTO> getAllPositions() {
        return setupsService.getAllPositions();
    }

    @PostMapping("/save-position")
    public ResponseEntity savePosition(@RequestBody PositionDTO positionDTO) {
        return setupsService.savePosition(positionDTO);
    }

    @PostMapping("/edit-position/{id}")
    public ResponseEntity updatePosition(@PathVariable(name = "id") UUID id, @RequestBody PositionDTO positionDTO){
        return setupsService.updatePosition(id, positionDTO);
    }

    @GetMapping("/get-position/{id}")
    public ResponseEntity getPositionById(@PathVariable(name = "id") UUID id){
        return setupsService.getPositionById(id);
    }

}
