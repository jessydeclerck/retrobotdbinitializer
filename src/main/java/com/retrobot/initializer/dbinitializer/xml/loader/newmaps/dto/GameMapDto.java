package com.retrobot.initializer.dbinitializer.xml.loader.newmaps.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameMapDto {

    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private int id;
    @JacksonXmlProperty(localName = "data")
    private DataDto data;
    @JacksonXmlProperty(localName = "size")
    private SizeDto size;
    @JacksonXmlProperty(localName = "position")
    private String position;
    @JacksonXmlProperty(localName = "triggers")
    private String triggers;

}
