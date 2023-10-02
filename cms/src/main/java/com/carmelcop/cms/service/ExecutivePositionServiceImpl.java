package com.carmelcop.cms.service;

import com.carmelcop.cms.dto.ExecutivePositionDTO;
import com.carmelcop.cms.model.ExecutivePosition;
import com.carmelcop.cms.repository.ExecutivePositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ExecutivePositionServiceImpl implements ExecutivePositionService {

    @Autowired
    ExecutivePositionRepository executivePositionRepository;

    @Override
    public List<ExecutivePositionDTO> getAllExecutivePositions() {
        List<ExecutivePositionDTO> executivePositionDTOS = new ArrayList<>();
        executivePositionRepository.findAll().forEach(e -> executivePositionDTOS.add(e.toDto()));
        return executivePositionDTOS;
    }

    @Override
    public ResponseEntity getExecutivePositionById(UUID id) {

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id cannot be empty");
        }

        Optional<ExecutivePosition> executivePosition = executivePositionRepository.findById(id);

        if (executivePosition.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Executive Position does not exist");
        }

        return new ResponseEntity(executivePosition.get().toDto(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getExecutivePositionsByActiveStatus(boolean active) {
        List<ExecutivePositionDTO> executivePositionDTOS = new ArrayList<>();

        executivePositionRepository.findAllByActiveEquals(active).forEach(e -> executivePositionDTOS.add(e.toDto()));

        return new ResponseEntity(executivePositionDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity saveExecutivePosition(ExecutivePositionDTO executivePositionDTO) {

        if (executivePositionDTO.getName() == null || executivePositionDTO.getName().equalsIgnoreCase("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Executive Position name cannot be empty");
        }

        if (executivePositionDTO.getCreatedBy() == null || executivePositionDTO.getCreatedBy().equalsIgnoreCase("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Created by cannot be empty");
        }

        ExecutivePosition executivePosition = executivePositionDTO.toEntity();
        executivePosition.setCreatedAt(ZonedDateTime.now());

        return new ResponseEntity<>(executivePositionRepository.save(executivePosition).toDto(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateExecutivePosition(UUID id, ExecutivePositionDTO executivePositionDTO) {

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be empty");
        }

        Optional<ExecutivePosition> position = executivePositionRepository.findById(id);

        if (position.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Executive Position does not exist");
        }

        if (executivePositionDTO.getName() == null || executivePositionDTO.getName().equalsIgnoreCase("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Executive Position name cannot be empty");
        }

        var newPosition = position.get();
        newPosition.setName(executivePositionDTO.getName());
        newPosition.setActive(executivePositionDTO.isActive());
        newPosition.setCreatedBy(executivePositionDTO.getCreatedBy());

        return new ResponseEntity(executivePositionRepository.save(newPosition).toDto(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateExecutivePositionActiveStatus(UUID id, boolean active) {

        if (id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be empty");
        }

        Optional<ExecutivePosition> executivePosition = executivePositionRepository.findById(id);

        if (executivePosition.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Executiv Position does not exist");
        }

        var position = executivePosition.get();
        position.setActive(active);

        return new ResponseEntity(executivePositionRepository.save(position).toDto(), HttpStatus.OK);
    }
}
