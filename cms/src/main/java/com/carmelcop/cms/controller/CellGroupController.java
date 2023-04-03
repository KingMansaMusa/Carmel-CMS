package com.carmelcop.cms.controller;

import com.carmelcop.cms.dto.CellGroupDTO;
import com.carmelcop.cms.repository.CellGroupRepository;
import com.carmelcop.cms.service.CellGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CellGroupController {

    @Autowired
    CellGroupService cellGroupService;

    @GetMapping("/get-cell-groups")
    public List<CellGroupDTO> getAllCellGroups() {
        return cellGroupService.getAllCellGroups();
    }

    @GetMapping("/get-cell-group/{id}")
    public ResponseEntity getCellGroupById(@PathVariable UUID id) {
        return cellGroupService.findCellGroupById(id);
    }

    @GetMapping("/get-active-cell-groups")
    public List<CellGroupDTO> getActiveCellGroups(@RequestParam boolean active) {
        return cellGroupService.getActiveCellGroups(active);
    }

    @PostMapping("/save-cell-group")
    public ResponseEntity saveCellGroup(@RequestBody CellGroupDTO cellGroupDTO) {
        return cellGroupService.saveCellGroup(cellGroupDTO);
    }

    @PostMapping("/edit-cell-group/{id}")
    public ResponseEntity updateCellGroup(@PathVariable UUID id, @RequestBody CellGroupDTO cellGroupDTO) {
        return cellGroupService.updateCellGroup(id, cellGroupDTO);
    }

    @PostMapping("/edit-cell-group-active/{id}")
    public ResponseEntity updateCellGroupActiveStatus(@PathVariable UUID id, @RequestParam boolean active){
        return cellGroupService.updateCellGroupActiveStatus(id, active);
    }

}
