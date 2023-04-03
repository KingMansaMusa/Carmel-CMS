package com.carmelcop.cms.service;

import com.carmelcop.cms.dto.MemberDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface MemberService {
    List<MemberDTO> getAllMembers();

    ResponseEntity saveMember(MemberDTO memberDTO);

    ResponseEntity getMemberById(UUID id);

    ResponseEntity updateMember(UUID id, MemberDTO memberDTO);

    ResponseEntity setMemberActiveStatus(UUID id, boolean active);

    ResponseEntity setMemberDeceasedStatus(UUID id, boolean deceased);

    List<MemberDTO> getMembersByActiveStatus(boolean active);

    List<MemberDTO> getMembersByDeceasedStatus(boolean deceased);
}
