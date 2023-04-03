package com.carmelcop.cms.controller;

import com.carmelcop.cms.dto.MemberDTO;
import com.carmelcop.cms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/get-members")
    public List<MemberDTO> getAllMembers() {
        return memberService.getAllMembers();
    }

    @Validated
    @PostMapping("/save-member")
    public ResponseEntity saveMember(@RequestBody MemberDTO memberDTO) {
        return memberService.saveMember(memberDTO);
    }

    @GetMapping("/get-member/{id}")
    public ResponseEntity getMemberById(@PathVariable(name = "id") UUID id) {
        return memberService.getMemberById(id);
    }

    @PostMapping("/edit-member/{id}")
    public ResponseEntity updateMember(@PathVariable(name = "id") UUID id, @RequestBody MemberDTO memberDTO) {
        return memberService.updateMember(id, memberDTO);
    }

    @PostMapping("/edit-member-active/{id}")
    public ResponseEntity updateMemberActiveStatus(@PathVariable(name = "id") UUID id,
                                                   @RequestParam("active") boolean active) {
        return memberService.setMemberActiveStatus(id, active);
    }

    @PostMapping("/edit-member-deceased/{id}")
    public ResponseEntity updateMemberDeceasedStatus(@PathVariable(name = "id") UUID id,
                                                     @RequestParam(name = "deceased") boolean deceased) {
        return memberService.setMemberDeceasedStatus(id, deceased);
    }

    @GetMapping("/get-active-members")
    public List<MemberDTO> getMembersByActiveStatus(@RequestParam(name = "active") boolean active){
        return memberService.getMembersByActiveStatus(active);
    }

    @GetMapping("/get-deceased-members")
    public List<MemberDTO> getMembersByDeceasedStatus(@RequestParam boolean deceased){
        return memberService.getMembersByDeceasedStatus(deceased);
    }

}
