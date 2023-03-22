package com.carmelcop.cms.dto;

import com.carmelcop.cms.model.Position;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class PositionDTO {

    private UUID id;
    private String name;

    private boolean active;
    private String createdBy;
    private ZonedDateTime createdAt;

    public Position toEntity() {
        Position position = new Position();
        position.setId(this.id);
        position.setName(this.name);
        position.setActive(this.active);
        position.setCreatedAt(this.createdAt);
        position.setCreatedBy(this.createdBy);

        return position;
    }
}
