package com.carmelcop.cms.model;

import com.carmelcop.cms.dto.ExecutiveDTO;
import com.carmelcop.cms.dto.ExecutivePositionDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Table(name = "tbl_executive")
@Entity
public class Executive {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private UUID groupId;

    @Column
    private UUID memberId;

    @Column
    private UUID positionId;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private String createdBy;


    public ExecutiveDTO toDto() {
        ExecutiveDTO executiveDTO = new ExecutiveDTO();
        executiveDTO.setId(this.id);
        executiveDTO.setGroupId(this.groupId);
        executiveDTO.setPositionId(this.positionId);
        executiveDTO.setMemberId(this.memberId);
        executiveDTO.setCreatedAt(this.createdAt);
        executiveDTO.setCreatedBy(this.createdBy);

        return executiveDTO;
    }

}
