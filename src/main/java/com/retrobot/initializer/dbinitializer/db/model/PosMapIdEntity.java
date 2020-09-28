package com.retrobot.initializer.dbinitializer.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "posmapid")
@Builder
@AllArgsConstructor
public class PosMapIdEntity {

    @Id
    private String pos;

    @Column(name = "map_id")
    private String mapId;

    public PosMapIdEntity() {

    }
}
