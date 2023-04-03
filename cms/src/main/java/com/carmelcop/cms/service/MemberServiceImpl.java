package com.carmelcop.cms.service;

import com.carmelcop.cms.dto.MemberDTO;
import com.carmelcop.cms.model.*;
import com.carmelcop.cms.repository.*;
import org.apache.tomcat.websocket.ReadBufferOverflowException;
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
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    CellGroupRepository cellGroupRepository;

    @Override
    public List<MemberDTO> getAllMembers() {
        List<MemberDTO> memberDTOS = new ArrayList<>();
        memberRepository.findAll().forEach(m -> memberDTOS.add(m.toDto()));
        return memberDTOS;
    }

    @Override
    public ResponseEntity saveMember(MemberDTO memberDTO) {
        Member member = memberDTO.toEntity();
        Optional<Position> position = positionRepository.findById(memberDTO.getPositionId());
        Optional<Gender> gender = genderRepository.findById(memberDTO.getGenderId());
        Optional<MaritalStatus> maritalStatus = maritalStatusRepository.findById(memberDTO.getMaritalStatusId());
        Optional<CellGroup> cellGroup = Optional.empty();

        if (memberDTO.getCellGroupId() != null) {
            cellGroup = cellGroupRepository.findById(memberDTO.getCellGroupId());
        }

        if (cellGroup.isEmpty() && memberDTO.getCellGroupId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cell Group does not exist");
        }

        if (position.isEmpty()) {
            return new ResponseEntity("Position does not exist", HttpStatus.NOT_FOUND);
        }

        if (gender.isEmpty()) {
            return new ResponseEntity("Gender does not exist", HttpStatus.NOT_FOUND);
        }

        if (maritalStatus.isEmpty()) {
            return new ResponseEntity("Marital Status does not exist", HttpStatus.NOT_FOUND);
        }

        member.setCreatedAt(ZonedDateTime.now());
        member.setPosition(position.get());
        member.setGender(gender.get());
        member.setMaritalStatus(maritalStatus.get());

        if (memberDTO.getCellGroupId() != null) {
            member.setCellGroup(cellGroup.get());
        }

        var newMember = memberRepository.save(member).toDto();
        newMember.setGenderId(member.getGender().getId());
        newMember.setPositionId(member.getPosition().getId());
        newMember.setMaritalStatusId(member.getMaritalStatus().getId());

        return new ResponseEntity(newMember, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getMemberById(UUID id) {

        if (id == null) {
            return new ResponseEntity("Member ID cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Optional<Member> member = memberRepository.findById(id);

        if (member.isEmpty()) {
            return new ResponseEntity("Member does not exist", HttpStatus.NOT_FOUND);
        }

        MemberDTO memberDTO = member.get().toDto();

        return new ResponseEntity(memberDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateMember(UUID id, MemberDTO memberDTO) {

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member ID cannot be empty");
        }

        Optional<Member> member = memberRepository.findById(id);

        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member does not exist");
        }

        Member newMember = member.get();

        if (memberDTO.getLastName() == null && memberDTO.getLastName().equalsIgnoreCase("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last Name cannot be empty");
        }

        newMember.setLastName(memberDTO.getLastName());

        if (memberDTO.getOtherNames() == null && memberDTO.getOtherNames().equalsIgnoreCase("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Other Names cannot be empty");
        }

        newMember.setOtherNames(memberDTO.getOtherNames());

        if (memberDTO.getDateOfBirth() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date of Birth cannot be empty");
        }

        newMember.setDateOfBirth(memberDTO.getDateOfBirth());

        if (memberDTO.getContact() == null && memberDTO.getContact().equalsIgnoreCase("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contact cannot be empty");
        }

        newMember.setContact(memberDTO.getContact());

        if (memberDTO.getNameOfSpouse() == null) {
            newMember.setNameOfSpouse("");
        }

        newMember.setActive(memberDTO.isActive());
        newMember.setDeceased(memberDTO.isDeceased());
        newMember.setEmail(memberDTO.getEmail());
        newMember.setNameOfSpouse(memberDTO.getNameOfSpouse());
        newMember.setNumberOfChildren(memberDTO.getNumberOfChildren());
        newMember.setNumberOfChildrenBelow13(memberDTO.getNumberOfChildrenBelow13());

        if (memberDTO.getOccupation() == null && memberDTO.getOccupation().equalsIgnoreCase("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Occupation cannot be empty");
        }

        newMember.setOccupation(memberDTO.getOccupation());

        if (memberDTO.getResidentialAddress() == null && memberDTO.getResidentialAddress().equalsIgnoreCase("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Residential Address cannot be empty");
        }

        newMember.setResidentialAddress(memberDTO.getResidentialAddress());

        if (memberDTO.getPositionId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Position cannot be empty");
        }

        if (memberDTO.getGenderId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gender cannot be empty");
        }

        if (memberDTO.getMaritalStatusId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Marital Status cannot be empty");
        }

        Optional<Position> position = positionRepository.findById(memberDTO.getPositionId());
        Optional<Gender> gender = genderRepository.findById(memberDTO.getGenderId());
        Optional<MaritalStatus> maritalStatus = maritalStatusRepository.findById(memberDTO.getMaritalStatusId());

        if (position.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Position does not exist");
        }

        newMember.setPosition(position.get());

        if (gender.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gender does not exist");
        }

        newMember.setGender(gender.get());

        if (maritalStatus.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marital Status does not exist");
        }

        newMember.setMaritalStatus(maritalStatus.get());

        MemberDTO newMemberDto = memberRepository.save(newMember).toDto();
        newMemberDto.setPositionId(newMember.getPosition().getId());
        newMemberDto.setGenderId(newMember.getGender().getId());
        newMemberDto.setMaritalStatusId(newMember.getMaritalStatus().getId());

        return new ResponseEntity(newMemberDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity setMemberActiveStatus(UUID id, boolean active) {

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be empty");
        }

        var member = memberRepository.findById(id);

        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member does not exist");
        }

        var newMember = member.get();
        newMember.setActive(active);

        return new ResponseEntity(memberRepository.save(newMember).toDto(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity setMemberDeceasedStatus(UUID id, boolean deceased) {

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be empty");
        }

        var member = memberRepository.findById(id);

        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member does not exist");
        }

        var newMember = member.get();
        newMember.setDeceased(deceased);

        return new ResponseEntity(memberRepository.save(newMember).toDto(), HttpStatus.OK);
    }

    @Override
    public List<MemberDTO> getMembersByActiveStatus(boolean active) {
        List<MemberDTO> members = new ArrayList<>();
        memberRepository.findAllByActiveEquals(active).forEach(m -> members.add(m.toDto()));
        return members;
    }

    @Override
    public List<MemberDTO> getMembersByDeceasedStatus(boolean deceased) {
        List<MemberDTO> memberDTOS = new ArrayList<>();
        memberRepository.findAllByDeceasedEquals(deceased).forEach(m -> memberDTOS.add(m.toDto()));
        return memberDTOS;
    }
}
