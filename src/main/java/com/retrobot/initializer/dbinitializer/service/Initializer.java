package com.retrobot.initializer.dbinitializer.service;

import com.retrobot.initializer.dbinitializer.db.model.MapEntity;
import com.retrobot.initializer.dbinitializer.db.model.PosMapIdEntity;
import com.retrobot.initializer.dbinitializer.db.model.TriggerEntity;
import com.retrobot.initializer.dbinitializer.db.repository.MapRepository;
import com.retrobot.initializer.dbinitializer.db.repository.PosMapIdRepository;
import com.retrobot.initializer.dbinitializer.xml.loader.newmaps.dto.GameMapDto;
import com.retrobot.initializer.dbinitializer.xml.loader.newmaps.dto.GameMapsDto;
import com.retrobot.initializer.dbinitializer.xml.loader.oldmaps.dto.MapDto;
import com.retrobot.initializer.dbinitializer.xml.loader.oldmaps.dto.MapsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class Initializer {

    private final MapRepository mapRepository;

    private final PosMapIdRepository posMapIdRepository;

    private final Map<Integer, MapDto> idMapDtoMap = new HashMap<>();

    private final Map<Integer, GameMapDto> idGameMapDtoMap = new HashMap<>();

    private Map<String, String> noBackgroundPosMapId = new HashMap<>();

    public Initializer(MapRepository mapRepository, GameMapsDto gameMapsDto, MapsDto mapsDto, PosMapIdRepository posMapIdRepository) {
        this.mapRepository = mapRepository;
        this.posMapIdRepository = posMapIdRepository;
        gameMapsDto.getGameMaps().forEach(gameMapDto -> idGameMapDtoMap.put(gameMapDto.getId(), gameMapDto));
        mapsDto.getMaps().forEach(mapDto -> idMapDtoMap.put(mapDto.getId(), mapDto));
    }

    @PostConstruct
    public void updateDb() {
        log.info("Starting db update");
        updatePosMapId();
        //updateMapData();
        log.info("Db update done. Exiting program...");
        System.exit(0);
    }

    private void updatePosMapId() {
        StreamSupport.stream(mapRepository.findAll().spliterator(), false).filter(m -> m.getBackgroundNum() != 0).forEach(mapEntity -> {
            populatePosMapIdDb(mapEntity);
        });
        StreamSupport.stream(mapRepository.findAll().spliterator(), false).filter(m -> m.getBackgroundNum() == 0).forEach(mapEntity -> {
            String pos = mapEntity.getPosX() + "," + mapEntity.getPosY();
            if (noBackgroundPosMapId.containsKey(pos)) {
                System.out.println("Map id " + mapEntity.getMapId() + " already registered for pos : " + pos);
                System.out.println("Map id registered " + noBackgroundPosMapId.get(pos) + " already registered for pos : " + pos);
                if (mapEntity.getMapId() < Integer.parseInt(noBackgroundPosMapId.get(pos))) { //smaller id wins
                    System.out.println("Map id " + mapEntity.getMapId() + " will be registered instead for : " + pos);
                    noBackgroundPosMapId.put(pos, String.valueOf(mapEntity.getMapId()));
                }
            } else {
                noBackgroundPosMapId.put(pos, String.valueOf(mapEntity.getMapId()));
            }
        });
        noBackgroundPosMapId.forEach((noBgPos, noBgMapId) -> {
            log.info("Initializing no bg pos map id {}", noBgMapId);
            if (!posMapIdRepository.existsById(noBgPos)) {
                log.info("Saving no bg pos map id {}", noBgMapId);
                posMapIdRepository.save(PosMapIdEntity.builder().pos(noBgPos).mapId(noBgMapId).build());
            }
        });
    }

    private void populatePosMapIdDb(MapEntity mapEntity) {
        log.info("Initializing pos map id {}", mapEntity.getMapId());
        String pos = mapEntity.getPosX() + "," + mapEntity.getPosY();
        if (posMapIdRepository.existsById(pos)) {
            System.out.println("Map id " + mapEntity.getMapId() + " already registered for pos : " + pos);
            System.out.println("Map id registered " + posMapIdRepository.findById(pos).get().getMapId() + " already registered for pos : " + pos);
            if (mapEntity.getMapId() < Integer.parseInt(posMapIdRepository.findById(pos).get().getMapId())) { //smaller id wins
                System.out.println("Map id " + mapEntity.getMapId() + " will be registered instead for : " + pos);
                posMapIdRepository.save(PosMapIdEntity.builder().pos(pos).mapId(String.valueOf(mapEntity.getMapId())).build());
            }
        } else {
            posMapIdRepository.save(PosMapIdEntity.builder().pos(pos).mapId(String.valueOf(mapEntity.getMapId())).build());
        }
    }

    private void updateMapData() {
        idGameMapDtoMap.forEach((idGameMap, gameMap) -> {
            log.info("Updating map {} data", idGameMap);
            MapDto oldMapDto = idMapDtoMap.containsKey(idGameMap) ? idMapDtoMap.get(idGameMap) : new MapDto();
            String[] pos = gameMap.getPosition().split(",");
            String posX = pos[0];
            String posY = pos[1];
            String zone = pos[2];
            final List<TriggerEntity> triggersEntities = new ArrayList<>();
            String[] triggers = gameMap.getTriggers().split(";");
            Arrays.stream(triggers).forEach(triggerString -> {
                if (StringUtils.isEmpty(triggerString)) {
                    return;
                }
                String[] triggerData = triggerString.split(",");
                TriggerEntity triggerEntity = TriggerEntity.builder()
                        .mapId(idGameMap)
                        .cellId(Integer.valueOf(triggerData[0]))
                        .nextMap(Integer.valueOf(triggerData[1]))
                        .nextCell(triggerData.length > 2 ? Integer.valueOf(triggerData[2]) : -1)
                        .build();
                triggersEntities.add(triggerEntity);
            });
            MapEntity mapEntity = MapEntity.builder()
                    .mapId(gameMap.getId())
                    .date(gameMap.getData().getDate())
                    .key(gameMap.getData().getKey())
                    .data(gameMap.getData().getData())
                    .posX(Integer.valueOf(posX))
                    .posY(Integer.valueOf(posY))
                    .zone(Integer.valueOf(zone))
                    .width(gameMap.getSize().getWidth())
                    .height(gameMap.getSize().getHeight())
                    .subscriber(oldMapDto.getSubscriber())
                    .backgroundNum(oldMapDto.getBackgroundNum())
                    .ambianceId(oldMapDto.getAmbianceId())
                    .bOutdoor(oldMapDto.isBOutdoor())
                    .canFight(oldMapDto.getCanFight())
                    .capabilities(oldMapDto.getCapabilities())
                    .musicId(oldMapDto.getMusicId())
                    .triggers(triggersEntities)
                    .build();
            mapRepository.save(mapEntity);
        });
    }

}
