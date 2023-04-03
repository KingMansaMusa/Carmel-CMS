package com.carmelcop.cms.dto;

import com.carmelcop.cms.model.CellGroup;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class CellGroupDTO {

    private UUID id;
    private String name;
    private boolean active;
    private String createdBy;
    private ZonedDateTime createdAt;

    public CellGroup toEntity() {
        CellGroup cellGroup = new CellGroup();

        cellGroup.setId(this.id);
        cellGroup.setName(this.name);
        cellGroup.setActive(this.active);
        cellGroup.setCreatedBy(this.createdBy);
        cellGroup.setCreatedAt(this.createdAt);

        return cellGroup;
    }

}
