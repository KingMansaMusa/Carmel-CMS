package com.carmelcop.cms.dto;

import com.carmelcop.cms.model.Gender;
import lombok.Data;

import javax.persistence.Column;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class GenderDTO {

    private UUID id;
    private String name;
    private boolean active;
    private String createdBy;
    private ZonedDateTime createdAt;

    public Gender toEntity() {
        Gender gender = new Gender();
        gender.setId(this.id);
        gender.setName(this.name);
        gender.setActive(this.isActive());
        gender.setCreatedBy(this.createdBy);
        gender.setCreatedAt(this.createdAt);
        return gender;
    }

}
