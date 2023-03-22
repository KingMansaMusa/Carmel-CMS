package com.carmelcop.cms.service;

import com.carmelcop.cms.dto.GenderDTO;
import com.carmelcop.cms.dto.PositionDTO;
import com.carmelcop.cms.model.Gender;
import com.carmelcop.cms.model.Position;
import com.carmelcop.cms.repository.GenderRepository;
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

    @Autowired
    GenderRepository genderRepository;



    //POSITION SETUP
    @Override
    public ResponseEntity savePosition(PositionDTO positionDTO) {
        Position position = new Position();
        position.setName(positionDTO.getName());
        position.setActive(positionDTO.isActive());
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
        newPosition.setActive(positionDTO.isActive());

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

    @Override
    public List<PositionDTO> getActivePositions() {
        List<PositionDTO> positionDTOS = new ArrayList<>();
        positionRepository.findPositionByActiveIsTrue().forEach(p -> positionDTOS.add(p.toDto()));
        return positionDTOS;
    }

    @Override
    public ResponseEntity setPositionActiveById(UUID id, boolean active) {

        if (id == null) {
            return new ResponseEntity("ID cannot be null", HttpStatus.BAD_REQUEST);
        }

        Optional<Position> position = positionRepository.findById(id);

        if (position.isEmpty()) {
            return new ResponseEntity("Id does not exist", HttpStatus.NOT_FOUND);
        }

        var newPosition = position.get();
        newPosition.setActive(active);

        return new ResponseEntity(positionRepository.save(newPosition), HttpStatus.OK);
    }



    //GENDER SETUP
    @Override
    public List<GenderDTO> getAllGenders() {
        List<GenderDTO> genderDTOS = new ArrayList<>();
        genderRepository.findAll().forEach(g -> genderDTOS.add(g.toDto()));

        return genderDTOS;
    }

    @Override
    public ResponseEntity saveGender(GenderDTO genderDTO) {

        if (genderDTO.getName() == null && genderDTO.getName().equalsIgnoreCase("")) {
            return new ResponseEntity("Name cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if (genderDTO.getCreatedBy() == null && genderDTO.getName().equalsIgnoreCase("")) {
            return new ResponseEntity("Created by cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Gender gender = new Gender();
        gender.setName(genderDTO.getName());
        gender.setActive(genderDTO.isActive());
        gender.setCreatedBy(genderDTO.getCreatedBy());
        gender.setCreatedAt(ZonedDateTime.now());

        genderDTO.setId(genderRepository.save(gender).getId());
        genderDTO.setCreatedAt(gender.getCreatedAt());

        return new ResponseEntity(genderDTO, HttpStatus.OK);
    }

    @Override
    public List<GenderDTO> getActiveGenders() {
        List<GenderDTO> genderDTOS = new ArrayList<>();
        genderRepository.findAllByActiveTrue().forEach(g -> genderDTOS.add(g.toDto()));
        return genderDTOS;
    }

    @Override
    public ResponseEntity updateGender(UUID id, GenderDTO genderDTO) {

        if (id == null) {
            return new ResponseEntity("Id cannot null", HttpStatus.BAD_REQUEST);
        }

        if (genderDTO.getName() == null && genderDTO.getName().equalsIgnoreCase("")) {
            return new ResponseEntity("Name cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Optional<Gender> gender = genderRepository.findById(id);

        if (gender.isEmpty()) {
            return new ResponseEntity("Gender does not exist", HttpStatus.NOT_FOUND);
        }

        Gender newGender = gender.get();
        newGender.setName(genderDTO.getName());
        newGender.setActive(genderDTO.isActive());

        return new ResponseEntity(genderRepository.save(newGender).toDto(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getGenderById(UUID id) {

        if (id == null) {
            return new ResponseEntity("Id cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Optional<Gender> gender = genderRepository.findById(id);

        if (gender.isEmpty()) {
            return new ResponseEntity("Id does not exist", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(gender.get().toDto(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity setGenderActiveById(UUID id, boolean active) {

        if (id == null) {
            return new ResponseEntity("Id cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Optional<Gender> gender = genderRepository.findById(id);

        if (gender.isEmpty()) {
            return new ResponseEntity("Gender does not exist", HttpStatus.NOT_FOUND);
        }

        var newGender = gender.get();
        newGender.setActive(active);

        return new ResponseEntity(genderRepository.save(newGender).toDto(), HttpStatus.OK);
    }
}
