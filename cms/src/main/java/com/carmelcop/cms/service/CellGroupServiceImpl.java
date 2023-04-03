package com.carmelcop.cms.service;

import com.carmelcop.cms.dto.CellGroupDTO;
import com.carmelcop.cms.model.CellGroup;
import com.carmelcop.cms.repository.CellGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CellGroupServiceImpl implements CellGroupService {

    @Autowired
    CellGroupRepository cellGroupRepository;

    @Override
    public List<CellGroupDTO> getAllCellGroups() {
        List<CellGroupDTO> cellGroupDTOS = new ArrayList<>();
        cellGroupRepository.findAll().forEach(c -> cellGroupDTOS.add(c.toDto()));
        return cellGroupDTOS;
    }

    @Override
    public ResponseEntity findCellGroupById(UUID id) {

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be empty");
        }

        var cellGroup = cellGroupRepository.findById(id);

        if (cellGroup.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cell Group does not exist");
        }

        return new ResponseEntity(cellGroup.get().toDto(), HttpStatus.OK);
    }

    @Override
    public List<CellGroupDTO> getActiveCellGroups(boolean active) {
        List<CellGroupDTO> cellGroupDTOS = new ArrayList<>();
        cellGroupRepository.findCellGroupByActiveEquals(active).forEach(c -> cellGroupDTOS.add(c.toDto()));
        return cellGroupDTOS;
    }

    @Override
    public ResponseEntity saveCellGroup(CellGroupDTO cellGroupDTO) {

        if (cellGroupDTO.getName() == null && cellGroupDTO.getName().equalsIgnoreCase("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cell Group name cannot be empty");
        }

        if (cellGroupDTO.getCreatedBy() == null && cellGroupDTO.getCreatedBy().equalsIgnoreCase("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Created By cannot be empty");
        }

        CellGroup cellGroup = new CellGroup();
        cellGroup.setName(cellGroupDTO.getName());
        cellGroup.setCreatedBy(cellGroupDTO.getCreatedBy());
        cellGroup.setActive(cellGroupDTO.isActive());
        cellGroup.setCreatedAt(ZonedDateTime.now());

        cellGroupDTO.setId(cellGroupRepository.save(cellGroup).getId());
        cellGroupDTO.setCreatedAt(cellGroup.getCreatedAt());

        return new ResponseEntity(cellGroupDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateCellGroup(UUID id, CellGroupDTO cellGroupDTO) {

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be empty");
        }

        var cellGroup = cellGroupRepository.findById(id);

        if (cellGroup.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cell Group does not exist");
        }

        var newCellGroup = cellGroup.get();
        newCellGroup.setName(cellGroupDTO.getName());
        newCellGroup.setActive(cellGroupDTO.isActive());

        return new ResponseEntity(cellGroupRepository.save(newCellGroup).toDto(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateCellGroupActiveStatus(UUID id, boolean active) {

        if (id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be empty");
        }

        var cellGroup = cellGroupRepository.findById(id);

        if (cellGroup.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cell Group does not exist");
        }

        var newCellGroup = cellGroup.get();
        newCellGroup.setActive(active);

        return new ResponseEntity(cellGroupRepository.save(newCellGroup).toDto(), HttpStatus.OK);
    }
}
