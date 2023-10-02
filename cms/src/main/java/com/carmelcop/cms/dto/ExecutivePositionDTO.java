package com.carmelcop.cms.dto;

import com.carmelcop.cms.model.Executive;
import com.carmelcop.cms.model.ExecutivePosition;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class ExecutivePositionDTO {

    private UUID id;
    private String name;
    private boolean active;
    private String createdBy;
    private ZonedDateTime createdAt;

    public ExecutivePosition toEntity() {
        ExecutivePosition executivePosition = new ExecutivePosition();

        executivePosition.setId(this.id);
        executivePosition.setName(this.name);
        executivePosition.setActive(this.active);
        executivePosition.setCreatedAt(this.createdAt);
        executivePosition.setCreatedBy(this.createdBy);

        return executivePosition;
    }

}
