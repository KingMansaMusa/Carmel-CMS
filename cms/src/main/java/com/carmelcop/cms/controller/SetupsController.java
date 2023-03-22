package com.carmelcop.cms.controller;

import com.carmelcop.cms.dto.GenderDTO;
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


    //POSITION SETUP
    @GetMapping("/get-positions")
    public List<PositionDTO> getAllPositions() {
        return setupsService.getAllPositions();
    }

    @GetMapping("/get-active-positions")
    public List<PositionDTO> getActivePositions() {
        return setupsService.getActivePositions();
    }

    @PostMapping("/save-position")
    public ResponseEntity savePosition(@RequestBody PositionDTO positionDTO) {
        return setupsService.savePosition(positionDTO);
    }

    @PostMapping("/edit-position/{id}")
    public ResponseEntity updatePosition(@PathVariable(name = "id") UUID id, @RequestBody PositionDTO positionDTO) {
        return setupsService.updatePosition(id, positionDTO);
    }

    @GetMapping("/get-position/{id}")
    public ResponseEntity getPositionById(@PathVariable(name = "id") UUID id) {
        return setupsService.getPositionById(id);
    }

    @PostMapping("/edit-position-active/{id}")
    public ResponseEntity setPositionActiveById(@PathVariable("id") UUID id, @RequestParam(name = "active") boolean active) {
        return setupsService.setPositionActiveById(id, active);
    }



    //GENDER SETUP

    @GetMapping("/get-genders")
    public List<GenderDTO> getAllGenders(){
       return  setupsService.getAllGenders();
    }

    @PostMapping("save-gender")
    public ResponseEntity saveGender(@RequestBody GenderDTO genderDTO){
        return setupsService.saveGender(genderDTO);
    }

    @GetMapping("get-active-genders")
    public List<GenderDTO> getAllActiveGenders(){
        return setupsService.getActiveGenders();
    }

    @PostMapping("/edit-gender/{id}")
    public ResponseEntity updateGender(@PathVariable("id") UUID id, @RequestBody GenderDTO genderDTO){
        return setupsService.updateGender(id, genderDTO);
    }

    @GetMapping("/get-gender/{id}")
    public ResponseEntity getGenderById(@PathVariable("id") UUID id){
        return setupsService.getGenderById(id);
    }

    @PostMapping("edit-gender-active/{id}")
    public ResponseEntity setGenderActiveById(@PathVariable(name = "id") UUID id, @RequestParam(name = "active") boolean active){
        return setupsService.setGenderActiveById(id, active);
    }

}
