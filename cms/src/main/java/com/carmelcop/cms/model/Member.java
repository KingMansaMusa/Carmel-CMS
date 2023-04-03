package com.carmelcop.cms.model;

import com.carmelcop.cms.dto.MemberDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_member")
public class Member {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String otherNames;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String contact;

    @Column
    private String email;

    @Column
    private String nameOfSpouse;

    @Column
    private int numberOfChildren;

    @Column
    private int numberOfChildrenBelow13;

    @Column(nullable = false)
    private String occupation;

    @Column(nullable = false)
    private String residentialAddress;

    @Column
    private boolean deceased;

    @Column
    private boolean active;

    @Column
    private String createdBy;

    @Column
    private ZonedDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Position position;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MaritalStatus maritalStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private CellGroup cellGroup;

    public MemberDTO toDto() {
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setId(this.id);
        memberDTO.setLastName(this.lastName);
        memberDTO.setOtherNames(this.otherNames);
        memberDTO.setDateOfBirth(this.dateOfBirth);
        memberDTO.setEmail(this.email);
        memberDTO.setContact(this.contact);
        memberDTO.setNameOfSpouse(this.nameOfSpouse);
        memberDTO.setNumberOfChildren(this.numberOfChildren);
        memberDTO.setNumberOfChildrenBelow13(this.numberOfChildrenBelow13);
        memberDTO.setOccupation(this.occupation);
        memberDTO.setResidentialAddress(this.residentialAddress);
        memberDTO.setDeceased(this.deceased);
        memberDTO.setActive(this.active);
        memberDTO.setGenderId(this.gender.getId());
        memberDTO.setMaritalStatusId(this.maritalStatus.getId());
        memberDTO.setPositionId(this.position.getId());
        memberDTO.setCreatedAt(this.createdAt);
        memberDTO.setCreatedBy(this.createdBy);

        if (cellGroup != null){
            memberDTO.setCellGroupId(this.cellGroup.getId());
        }

        return memberDTO;
    }

}
