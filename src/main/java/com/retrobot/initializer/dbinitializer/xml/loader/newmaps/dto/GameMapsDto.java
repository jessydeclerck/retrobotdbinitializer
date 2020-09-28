package com.retrobot.initializer.dbinitializer.xml.loader.newmaps.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "GameMaps")
public class GameMapsDto {

    @JacksonXmlProperty(localName = "GameMap")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<GameMapDto> gameMaps = new ArrayList<>();

}
