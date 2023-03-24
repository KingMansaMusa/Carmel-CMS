package com.carmelcop.cms.service;

import com.carmelcop.cms.dto.GenderDTO;
import com.carmelcop.cms.dto.MaritalStatusDTO;
import com.carmelcop.cms.dto.PositionDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface SetupsService {

    public ResponseEntity savePosition(PositionDTO positionDTO);

    List<PositionDTO> getAllPositions();

    ResponseEntity updatePosition(UUID id, PositionDTO positionDTO);

    ResponseEntity getPositionById(UUID id);

    List<PositionDTO> getActivePositions();

    ResponseEntity setPositionActiveById(UUID id, boolean active);

    List<GenderDTO> getAllGenders();

    ResponseEntity saveGender(GenderDTO genderDTO);

    List<GenderDTO> getActiveGenders();

    ResponseEntity updateGender(UUID id, GenderDTO genderDTO);

    ResponseEntity getGenderById(UUID id);

    ResponseEntity setGenderActiveById(UUID id, boolean active);



    //MARITAL STATUS SETUP

    ResponseEntity saveMaritalStatus(MaritalStatusDTO maritalStatusDTO);

    List<MaritalStatusDTO> getAllMaritalStatuses();

    ResponseEntity updateMaritalStatus(UUID id, MaritalStatusDTO maritalStatusDTO);

    ResponseEntity getMaritalStatusById(UUID id);

    List<MaritalStatusDTO> getActiveMaritalStatuses();

    ResponseEntity setMaritalStatusActiveById(UUID id, boolean active);
}
