package com.carmelcop.cms.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.carmelcop.cms.dto.PositionDTO;
import com.carmelcop.cms.model.Position;
import com.carmelcop.cms.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SetupsServiceImpl implements SetupsService {

    @Autowired
    PositionRepository positionRepository;

    @Override
    public ResponseEntity savePosition(PositionDTO positionDTO) {
        Position position = new Position();
        position.setName(positionDTO.getName());
        position.setCreatedBy(positionDTO.getCreatedBy());
        position.setCreatedAt(ZonedDateTime.now());

        if (positionDTO.getName() == null && positionDTO.getName().equalsIgnoreCase("")) {
            return new ResponseEntity("Position name cannot me empty", HttpStatus.BAD_REQUEST);
        }

        if (positionDTO.getCreatedBy() == null && positionDTO.getCreatedBy().equalsIgnoreCase("")) {
            return new ResponseEntity("Created By cannot be empty", HttpStatus.BAD_REQUEST);
        }

        positionDTO.setId(positionRepository.save(position).getId());
        positionDTO.setCreatedAt(position.getCreatedAt());

        return new ResponseEntity(positionDTO, HttpStatus.OK);
    }

    @Override
    public List<PositionDTO> getAllPositions() {
        List<PositionDTO> positionDTOS = new ArrayList<>();
        positionRepository.findAll().forEach(p -> positionDTOS.add(p.toDto()));

        return positionDTOS;
    }

    @Override
    public ResponseEntity updatePosition(UUID id, PositionDTO positionDTO) {

        if (id == null) {
            return new ResponseEntity("Position ID cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Optional<Position> currentPosition = positionRepository.findById(id);

        if (positionDTO.getName() == null && positionDTO.getName().equalsIgnoreCase("")) {
            return new ResponseEntity("Position name cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if (currentPosition.isEmpty()) {
            return new ResponseEntity("The position you are editing does not exist", HttpStatus.NOT_FOUND);
        }

        var newPosition = currentPosition.get();
        newPosition.setName(positionDTO.getName());

        return new ResponseEntity(positionRepository.save(newPosition).toDto(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPositionById(UUID id) {

        if (id == null) {
            return new ResponseEntity("Id cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Optional<Position> position = positionRepository.findById(id);

        if (position.isEmpty()) {
            return new ResponseEntity("Position does not exist", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(position.get().toDto(), HttpStatus.OK);
    }
}
