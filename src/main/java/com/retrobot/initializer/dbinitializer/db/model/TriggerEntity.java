package com.retrobot.initializer.dbinitializer.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "triggers")
@IdClass(TriggerEntity.class)
@Builder
@AllArgsConstructor
public class TriggerEntity implements Serializable {

    @Id
    @Column(name = "map_id")
    private Integer mapId;

    @Id
    private Integer cellId;

    @Id
    private Integer nextMap;

    @Id
    private Integer nextCell;

    public TriggerEntity() {

    }
}
