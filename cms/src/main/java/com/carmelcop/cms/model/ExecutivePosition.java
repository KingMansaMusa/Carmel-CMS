package com.carmelcop.cms.model;

import com.carmelcop.cms.dto.ExecutiveDTO;
import com.carmelcop.cms.dto.ExecutivePositionDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Table(name = "tbl_executive_position")
@Entity
public class ExecutivePosition {

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


    public ExecutivePositionDTO toDto() {
        ExecutivePositionDTO executivePositionDTO = new ExecutivePositionDTO();

        executivePositionDTO.setId(this.id);
        executivePositionDTO.setName(this.name);
        executivePositionDTO.setActive(this.active);
        executivePositionDTO.setCreatedAt(this.createdAt);
        executivePositionDTO.setCreatedBy(this.createdBy);

        return executivePositionDTO;
    }

}
