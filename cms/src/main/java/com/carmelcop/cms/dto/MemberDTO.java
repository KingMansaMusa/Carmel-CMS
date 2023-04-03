package com.carmelcop.cms.dto;


import com.carmelcop.cms.model.Gender;
import com.carmelcop.cms.model.Member;
import com.carmelcop.cms.model.Position;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class MemberDTO {

    private UUID id;
    private String lastName;
    private String otherNames;
    private LocalDate dateOfBirth;
    private String contact;
    private String email;
    private String nameOfSpouse;
    private int numberOfChildren;
    private int numberOfChildrenBelow13;
    private String occupation;
    private String residentialAddress;

    private boolean deceased;

    private boolean active;

    private String createdBy;
    private ZonedDateTime createdAt;
    private UUID positionId;
    private UUID genderId;
    private UUID maritalStatusId;
    private UUID cellGroupId;

    public Member toEntity() {
        Member member = new Member();

        member.setId(this.id);
        member.setLastName(this.lastName);
        member.setOtherNames(this.otherNames);
        member.setEmail(this.email);
        member.setDateOfBirth(this.dateOfBirth);
        member.setContact(this.contact);
        member.setNameOfSpouse(this.nameOfSpouse);
        member.setNumberOfChildren(this.numberOfChildren);
        member.setNumberOfChildrenBelow13(this.numberOfChildrenBelow13);
        member.setOccupation(this.occupation);
        member.setResidentialAddress(this.residentialAddress);
        member.setDeceased(this.deceased);
        member.setActive(this.active);
        member.setCreatedAt(this.createdAt);
        member.setCreatedBy(this.createdBy);

        return member;
    }


}
