package com.carmelcop.cms.model;

import com.carmelcop.cms.dto.GenderDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_gender")
public class Gender {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column
    private boolean active;

    @Column
    private String createdBy;

    @Column
    private ZonedDateTime createdAt;

    public GenderDTO toDto() {
        GenderDTO genderDTO = new GenderDTO();
        genderDTO.setId(this.id);
        genderDTO.setName(this.name);
        genderDTO.setActive(this.isActive());
        genderDTO.setCreatedBy(this.createdBy);
        genderDTO.setCreatedAt(this.createdAt);
        return genderDTO;
    }

}
