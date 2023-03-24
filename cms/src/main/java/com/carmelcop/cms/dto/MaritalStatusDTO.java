package com.carmelcop.cms.dto;

import com.carmelcop.cms.model.MaritalStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class MaritalStatusDTO {

    private UUID id;
    private String name;

    private boolean active;
    private String createdBy;
    private ZonedDateTime createdAt;

    public MaritalStatus toEntity() {
        MaritalStatus maritalStatus = new MaritalStatus();
        maritalStatus.setId(this.id);
        maritalStatus.setName(this.name);
        maritalStatus.setActive(this.active);
        maritalStatus.setCreatedAt(this.createdAt);
        maritalStatus.setCreatedBy(this.createdBy);

        return maritalStatus;
    }

}
