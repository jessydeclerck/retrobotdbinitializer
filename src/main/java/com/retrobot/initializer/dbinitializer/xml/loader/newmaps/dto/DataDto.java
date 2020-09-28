package com.retrobot.initializer.dbinitializer.xml.loader.newmaps.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

@Data
public class DataDto {

    @JacksonXmlProperty(localName = "date", isAttribute = true)
    private String date;

    @JacksonXmlProperty(localName = "key", isAttribute = true)
    private String key;

    @JacksonXmlText
    private String data;


}
