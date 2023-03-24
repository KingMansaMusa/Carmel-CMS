package com.carmelcop.cms.model;

import com.carmelcop.cms.dto.MaritalStatusDTO;
import lombok.CustomLog;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_marital_status")
public class MaritalStatus {

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

    public MaritalStatusDTO toDto() {
        MaritalStatusDTO maritalStatusDTO = new MaritalStatusDTO();

        maritalStatusDTO.setId(this.id);
        maritalStatusDTO.setName(this.name);
        maritalStatusDTO.setActive(this.active);
        maritalStatusDTO.setCreatedAt(this.createdAt);
        maritalStatusDTO.setCreatedBy(this.createdBy);

        return maritalStatusDTO;
    }
}
