package com.carmelcop.cms.model;

import com.carmelcop.cms.dto.CellGroupDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_cell_group")
public class CellGroup {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private boolean active;

    @Column
    private String createdBy;

    @Column
    private ZonedDateTime createdAt;

    public CellGroupDTO toDto() {
        CellGroupDTO cellGroupDTO = new CellGroupDTO();

        cellGroupDTO.setId(this.id);
        cellGroupDTO.setName(this.name);
        cellGroupDTO.setActive(this.active);
        cellGroupDTO.setCreatedBy(this.createdBy);
        cellGroupDTO.setCreatedAt(this.createdAt);

        return cellGroupDTO;
    }

}
