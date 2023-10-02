package com.carmelcop.cms.service;

import com.carmelcop.cms.dto.ExecutiveDTO;
import com.carmelcop.cms.model.*;
import com.carmelcop.cms.repository.CellGroupRepository;
import com.carmelcop.cms.repository.ExecutivePositionRepository;
import com.carmelcop.cms.repository.ExecutiveRepository;
import com.carmelcop.cms.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExecutiveServiceImpl implements ExecutiveService {

    @Autowired
    ExecutiveRepository executiveRepository;

    @Autowired
    CellGroupRepository cellGroupRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ExecutivePositionRepository executivePositionRepository;

    @Override
    public List<ExecutiveDTO> getAllExecutives() {
        List<ExecutiveDTO> executiveDTOS = new ArrayList<>();
        executiveRepository.findAll().forEach(e -> executiveDTOS.add(e.toDto()));
        return executiveDTOS;
    }

    @Override
    public ResponseEntity getExecutiveById(UUID id) {

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be null");
        }

        Optional<Executive> executive = executiveRepository.findById(id);

        if (executive.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Executive cannot be found");
        }

        return new ResponseEntity(executive.get().toDto(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getExecutivesByGroupId(UUID groupId) {

        List<ExecutiveDTO> executiveDTOS = new ArrayList<>();

        if (groupId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group ID cannot be null");
        }

        Optional<CellGroup> cellGroup = cellGroupRepository.findById(groupId);


        if (cellGroup.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group does not exist");
        }

        executiveRepository.findAllByGroupIdEquals(groupId).forEach(e -> executiveDTOS.add(e.toDto()));

        return new ResponseEntity(executiveDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getExecutivesByMemberId(UUID memberId) {

        List<ExecutiveDTO> executiveDTOS = new ArrayList<>();

        if (memberId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member ID cannot be null");
        }

        Optional<Member> member = memberRepository.findById(memberId);

        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member does not exist");
        }

        executiveRepository.findAllByMemberIdEquals(memberId).forEach(e -> executiveDTOS.add(e.toDto()));

        return new ResponseEntity(executiveDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getExecutivesByPositionId(UUID positionId) {

        List<ExecutiveDTO> executiveDTOS = new ArrayList<>();

        if (positionId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Position ID cannot be null");
        }

        Optional<ExecutivePosition> executivePosition = executivePositionRepository.findById(positionId);

        if (executivePosition.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Executive Position does not exist");
        }

        executiveRepository.findAllByPositionIdEquals(positionId).forEach(e -> executiveDTOS.add(e.toDto()));

        return new ResponseEntity(executiveDTOS, HttpStatus.OK);

    }

    @Override
    public ResponseEntity updateExecutive(UUID id, ExecutiveDTO executiveDTO) {

        if (id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be null");
        }

        Optional<Executive> executive = executiveRepository.findById(id);

        if (executive.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Executive does not exist");
        }

        

        return null;
    }
}
