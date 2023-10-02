package com.carmelcop.cms.controller;

import com.carmelcop.cms.dto.ExecutiveDTO;
import com.carmelcop.cms.model.Executive;
import com.carmelcop.cms.service.ExecutiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ExecutiveController {

    @Autowired
    ExecutiveService executiveService;

    @GetMapping("/get-executives")
    public List<ExecutiveDTO> getAllExecutives(){
        return executiveService.getAllExecutives();
    }

    @GetMapping("/get-executive/{id}")
    public ResponseEntity getExecutiveById(@PathVariable(name = "id") UUID id){
        return executiveService.getExecutiveById(id);
    }

    @GetMapping("/get-executive-group/{groupId}")
    public ResponseEntity getExecutivesByGroupId(@PathVariable(name = "groupId") UUID groupId){
        return executiveService.getExecutivesByGroupId(groupId);
    }

    @GetMapping("/get-executive-member/{memberId}")
    public ResponseEntity getExecutivesByMemberId(@PathVariable(name = "memberId") UUID memberId){
        return executiveService.getExecutivesByMemberId(memberId);
    }

    @GetMapping("/get-executive-position/{positionId}")
    public ResponseEntity getExecutivesByPositionId(@PathVariable(name = "positionId") UUID positionId){
        return executiveService.getExecutivesByPositionId(positionId);
    }

    @PutMapping("/edit-executive-position/{id}")
    public ResponseEntity updateExecutive(@PathVariable(name = "id") UUID id,
                                          @RequestBody ExecutiveDTO executiveDTO){
        return executiveService.updateExecutive(id, executiveDTO);
    }

}
