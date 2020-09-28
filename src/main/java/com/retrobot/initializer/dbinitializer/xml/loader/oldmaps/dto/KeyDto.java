package com.retrobot.initializer.dbinitializer.xml.loader.oldmaps.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class KeyDto {
    @JacksonXmlProperty(localName = "value", isAttribute = true)
    private String value;
}
