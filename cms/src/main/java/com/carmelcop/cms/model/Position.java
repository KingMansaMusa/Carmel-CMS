package com.carmelcop.cms.model;

import com.carmelcop.cms.dto.PositionDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_position")
public class Position {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column
    private String createdBy;

    @Column
    private ZonedDateTime createdAt;

    public PositionDTO toDto() {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setId(this.getId());
        positionDTO.setName(this.getName());
        positionDTO.setCreatedAt(this.getCreatedAt());
        positionDTO.setCreatedBy(this.getCreatedBy());

        return positionDTO;
    }

}
