package com.retrobot.initializer.dbinitializer.xml.loader.newmaps.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class SizeDto {

    @JacksonXmlProperty(localName = "width", isAttribute = true)
    private Integer width;

    @JacksonXmlProperty(localName = "height", isAttribute = true)
    private Integer height;


}
