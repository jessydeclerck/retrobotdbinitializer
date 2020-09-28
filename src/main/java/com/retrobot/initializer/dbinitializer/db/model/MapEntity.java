package com.retrobot.initializer.dbinitializer.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "maps")
@Builder
@AllArgsConstructor
public class MapEntity {

    @Id
    @Column(name = "map_id")
    private Integer mapId;

    private String date;

    private String key;

    private String data;

    @Column(name = "pos_x")
    private Integer posX;

    @Column(name = "pos_y")
    private Integer posY;

    private Integer zone;

    private Integer width;

    private Integer height;

    private Integer subscriber;

    @Column(name = "can_fight")
    private Integer canFight;

    @Column(name = "background_num")
    private Integer backgroundNum;

    @Column(name = "ambiance_id")
    private Integer ambianceId;

    @Column(name = "music_id")
    private Integer musicId;

    @Column(name = "b_outdoor")
    private Boolean bOutdoor;

    private Integer capabilities;

    @OneToMany(mappedBy = "mapId", cascade = CascadeType.ALL)
    private List<TriggerEntity> triggers;

    public MapEntity() {

    }
}
