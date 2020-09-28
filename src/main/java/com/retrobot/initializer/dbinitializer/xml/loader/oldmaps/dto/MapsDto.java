package com.retrobot.initializer.dbinitializer.xml.loader.oldmaps.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "maps")
public class MapsDto {

    @JacksonXmlProperty(localName = "map")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<MapDto> maps = new ArrayList<>();

}
