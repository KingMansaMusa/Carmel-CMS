package com.carmelcop.cms.dto;


import com.carmelcop.cms.model.Executive;
import com.carmelcop.cms.model.ExecutivePosition;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class ExecutiveDTO {

    private UUID id;
    private UUID groupId;
    private String group;
    private UUID memberId;
    private String member;
    private UUID positionId;
    private String position;
    private ZonedDateTime createdAt;
    private String createdBy;

    public Executive toEntity() {
        Executive executive = new Executive();
        executive.setId(this.id);
        executive.setMemberId(this.memberId);
        executive.setGroupId(this.groupId);
        executive.setPositionId(this.positionId);
        executive.setCreatedAt(this.createdAt);
        executive.setCreatedBy(this.createdBy);

        return executive;
    }

}
